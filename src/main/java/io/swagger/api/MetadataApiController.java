package io.swagger.api;

import io.swagger.dbo.MetadataRowMapper;
import io.swagger.dbo.Util;
import io.swagger.model.Metadata;
import io.swagger.model.Metadatas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
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

    @org.springframework.beans.factory.annotation.Autowired
    public MetadataApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Metadatas> getMetadata() {
        String accept = request.getHeader("Accept");
        String viewerSchemaName = Util.getDefaultViewerSchema();

        if (accept != null && accept.contains("application/json")) {
            Metadatas metadatas = new Metadatas();
            List<Metadata> searchResult = viewerJdbcTemplate.query("SELECT m.metadata_id AS MetadataId, m.name AS Name, m.description AS Description, m.tag AS Tag, m.viewer_config AS ViewerConfig FROM " + viewerSchemaName + ".metadata m", new MetadataRowMapper());
            if (searchResult != null && !searchResult.isEmpty()) {
                metadatas.setCount(searchResult.size());
                metadatas.setMetadatas(searchResult);
            } else {
                metadatas.setCount(0);
            }

            return new ResponseEntity<Metadatas>(metadatas, HttpStatus.OK);
        }

        return new ResponseEntity<Metadatas>(HttpStatus.NOT_ACCEPTABLE);
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
            + "SET name = '" + name + "', description = '" + description + "', viewer_config = '" + viewerConfig + "'"
            + "WHERE tag = '" + tag + "'";

        viewerJdbcTemplate.update(sql);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
