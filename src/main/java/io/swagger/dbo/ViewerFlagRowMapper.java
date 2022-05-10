package io.swagger.dbo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.ViewerFlag;

public class ViewerFlagRowMapper implements RowMapper<ViewerFlag> {
    private Map<Integer, ViewerFlag> resultMap;

    public ViewerFlagRowMapper() {
        resultMap = new HashMap<Integer, ViewerFlag>();
    }
    
    public Map<Integer, ViewerFlag> getResultMap() {
        return this.resultMap;
    }

    @Override
    public ViewerFlag mapRow(ResultSet rs, int rowNum) throws SQLException {
        ViewerFlag viewerFlag = new ViewerFlag();

        Integer contentId = rs.getInt("content_id");

        viewerFlag.setContentId(contentId);
        viewerFlag.setFlag(rs.getString("flag"));
        viewerFlag.setCaseId(rs.getInt("case_id"));

        resultMap.put(contentId, viewerFlag);
        
        return viewerFlag;
    }
    
}
