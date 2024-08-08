package io.swagger.api;

import io.swagger.dbo.MetadataRowMapper;
import io.swagger.dbo.Util;
import io.swagger.model.Metadata;
import io.swagger.model.Metadatas;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-03-29T13:22:28.494Z[GMT]")
@RestController
public class MetadataApiController implements MetadataApi {

    private static final Logger log = LoggerFactory.getLogger(MetadataApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    @Qualifier("viewerJdbcTemplate")
    private JdbcTemplate viewerJdbcTemplate;

    @Value("${server.version}")
    private String version;

    @Autowired
    public MetadataApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    JwtDecoder jwtDecoder;

    public ResponseEntity<Metadatas> getMetadata() {
        String viewerSchemaName = Util.getDefaultViewerSchema();

        // if (accept != null && accept.contains("application/json")) {
        Metadatas metadatas = new Metadatas();
        metadatas.setVersion(version);
        metadatas.setMetadatas(new ArrayList<Metadata>());
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        int metaDataSize = 0;
        if (authHeader != null && !authHeader.isBlank()) {
            String[] authHeaders = authHeader.trim().split(" ");
            if (authHeaders.length == 2 && "bearer".equalsIgnoreCase(authHeaders[0])) {
                // process the token and find the scopes (or permission)
                String token = authHeaders[1];

                // get the token decoded.
                Jwt decodedJwt = jwtDecoder.decode(token);
                String scopeSet = decodedJwt.getClaim("scope");
                if (scopeSet != null && !scopeSet.isBlank()) {
                    List<Metadata> searchResult = viewerJdbcTemplate.query(
                        "SELECT m.metadata_id AS MetadataId, m.name AS Name, m.description AS Description, m.tag AS Tag, m.viewer_config AS ViewerConfig FROM "
                                + viewerSchemaName + ".metadata m",
                        new MetadataRowMapper());

                    String[] scopes = scopeSet.trim().split(" ");
                    for (String scope : scopes) {
                        String[] scope_ = scope.trim().split(":");
                        if (scope_.length == 2) {
                            String registryName = scope_[1];
                            for (Metadata metadata : searchResult) {
                                if (metadata.getTag().equalsIgnoreCase(registryName)) {
                                    if (metadatas.findMetadataItem(registryName) == null) {
                                        metadatas.addMetadataItem(metadata);
                                        metaDataSize++;    
                                    }
                                    break;
                                }
                            }
                        }

                    }
                }
            }
        }

        metadatas.setCount(metaDataSize);

        return new ResponseEntity<Metadatas>(metadatas, HttpStatus.OK);
        // }

        // return new ResponseEntity<Metadatas>(HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Void> addMetadata(@NotNull @Valid Metadata body) {
        String sql;
        String viewerSchemaName = Util.getDefaultViewerSchema();

        String name = body.getName();
        String description = body.getDescription();
        String tag = body.getTag();
        String viewerConfig = body.getViewerConfig().toString();

        sql = "INSERT INTO " + viewerSchemaName + ".metadata "
                + "(name, description, tag, viewer_config) "
                + "VALUES ('" + name + "', '" + description + "', '" + tag + "', '" + viewerConfig + "')";

        viewerJdbcTemplate.update(sql);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> editMetadata(@NotNull @Valid String tag, @Valid Metadata body) {
        String sql;
        String viewerSchemaName = Util.getDefaultViewerSchema();

        String name = body.getName();
        String description = body.getDescription();
        String viewerConfig = body.getViewerConfig().toString();

        sql = "UPDATE " + viewerSchemaName + ".metadata "
                + "SET name = '" + name + "', description = '" + description + "', viewer_config = '" + viewerConfig
                + "'"
                + "WHERE tag = '" + tag + "'";

        viewerJdbcTemplate.update(sql);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
