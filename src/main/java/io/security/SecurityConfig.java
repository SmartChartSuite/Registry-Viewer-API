package io.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import io.swagger.dbo.MetadataRowMapper;
import io.swagger.dbo.Util;
import io.swagger.model.Metadata;

@EnableWebSecurity
public class SecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Value("${spring.security.oauth2.resourceserver.jwt.audiences}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Autowired
    @Qualifier("viewerJdbcTemplate")
    private JdbcTemplate viewerJdbcTemplate;

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry 
            interceptUrlRegistry = http.csrf(AbstractHttpConfigurer::disable).authorizeRequests();

        String viewerSchemaName = Util.getDefaultViewerSchema();

        // Get list of available registries.
        List<Metadata> metadatas = viewerJdbcTemplate.query("SELECT m.metadata_id AS MetadataId, m.name AS Name, m.description AS Description, m.tag AS Tag, m.viewer_config AS ViewerConfig FROM " + viewerSchemaName + ".metadata m", new MetadataRowMapper());
        log.debug("Read meta data from schema: " + viewerSchemaName);

        for (Metadata metadata : metadatas) {
            String registry = metadata.getTag();

            // For /case-record API
            String path = "/case-record/" + registry;
            String scopeRead = "SCOPE_read:" + registry;
            String scopeWrite = "SCOPE_write:" + registry;
            interceptUrlRegistry
                .mvcMatchers(HttpMethod.PUT, path).hasAnyAuthority(scopeWrite)
                .mvcMatchers(HttpMethod.GET, path).hasAnyAuthority(scopeRead);

            log.debug("path: " + path + " with " + scopeRead + " and " + scopeWrite);

            // for /questions API
            path = "/questions/" + registry;
            interceptUrlRegistry.mvcMatchers(path).hasAnyAuthority(scopeRead);

            log.debug("path: " + path + " with " + scopeRead + " and " + scopeWrite);

            // for /search-cases API
            path = "/search-cases/" + registry;
            interceptUrlRegistry.mvcMatchers(path).hasAnyAuthority(scopeRead);

            log.debug("path: " + path + " with " + scopeRead + " and " + scopeWrite);

        }
        interceptUrlRegistry
            .mvcMatchers(HttpMethod.POST, "/metadata").hasAnyAuthority("SCOPE_write:metadata")
            .mvcMatchers(HttpMethod.PUT, "/metadata").hasAnyAuthority("SCOPE_write:metadata")
            .mvcMatchers(HttpMethod.GET, "/metadata").permitAll()
            .mvcMatchers(HttpMethod.GET, "/**").denyAll()
            .and().cors().and().oauth2ResourceServer().jwt();

        // http.csrf(AbstractHttpConfigurer::disable).authorizeRequests()
        //         .mvcMatchers("/case-record").authenticated()
        //         .mvcMatchers(HttpMethod.PUT, "/case-record").hasAnyAuthority("SCOPE_write:syphilis", "SCOPE_write:scd")
        //         .mvcMatchers(HttpMethod.GET, "/case-record/syphilis").hasAnyAuthority("SCOPE_read:syphilis", "SCOPE_read:scd")
        //         .mvcMatchers("/questions").authenticated()
        //         .mvcMatchers("/questions").hasAnyAuthority("SCOPE_read:syphilis", "SCOPE_read:scd")
        //         .mvcMatchers("/search-cases").authenticated()
        //         .mvcMatchers("/search-cases").hasAnyAuthority("SCOPE_read:syphilis", "SCOPE_read:scd")
        //         .mvcMatchers("/*").permitAll()
        //         .and().cors()
        //         .and().oauth2ResourceServer().jwt();
        return http.build();
    }
}

