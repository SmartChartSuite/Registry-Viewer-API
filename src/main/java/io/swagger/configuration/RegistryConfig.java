package io.swagger.configuration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.swagger.dbo.MetadataRowMapper;
import io.swagger.dbo.Util;
import io.swagger.model.Metadata;

@Component
public class RegistryConfig {
    private static final Logger log = LoggerFactory.getLogger(RegistryConfig.class);

    @Autowired
    @Qualifier("viewerJdbcTemplate")
    private JdbcTemplate viewerJdbcTemplate;

    public boolean isValidRegistry(String registry) {
        if (registry == null || registry.isBlank()) {
            return false;
        }

        String viewerSchemaName = Util.getDefaultViewerSchema();

        // Get list of available registries.
        List<Metadata> metadatas = viewerJdbcTemplate.query("SELECT m.metadata_id AS MetadataId, m.name AS Name, m.description AS Description, m.tag AS Tag, m.viewer_config AS ViewerConfig FROM " + viewerSchemaName + ".metadata m", new MetadataRowMapper());
        log.debug("Read meta data to check if registry, " + registry + ", is valid.");

        for (Metadata metadata : metadatas) {
            if (registry.equals(metadata.getTag())) {
                return true;
            }
        }

        return false;
    }    
}
