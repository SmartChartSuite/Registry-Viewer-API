package io.swagger.dbo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.Metadata;

public class MetadataRowMapper implements RowMapper<Metadata> {
    private Map<Integer, Metadata> metadataMap = new HashMap<Integer, Metadata> ();
    
    public Map<Integer, Metadata> getQuestionMap() {
        return this.metadataMap;
    }

    @Override
    public Metadata mapRow(ResultSet rs, int rowNum) throws SQLException {
        Metadata metadata = new Metadata();
        metadata.setName(rs.getString("Name"));
        metadata.setDescription(rs.getString("Description"));
        metadata.setTag(rs.getString("Tag"));
        String viewerConfig = rs.getString("ViewerConfig");
        if (viewerConfig == null || viewerConfig.isBlank() || "null".equals(viewerConfig)) {
            metadata.setViewerConfig(null);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode viewerConfigJson;
            try {
                viewerConfigJson = mapper.readTree(viewerConfig);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                viewerConfigJson = null;
            }
            metadata.setViewerConfig(viewerConfigJson);
        }

        metadataMap.put(rs.getInt("MetadataId"), metadata);

        return metadata;
    }

}
