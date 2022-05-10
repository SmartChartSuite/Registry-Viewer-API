package io.swagger.dbo;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.ViewerAnnotation;

public class ViewerAnnotationRowMapper implements RowMapper<ViewerAnnotation> {
    private Map<Integer, List<ViewerAnnotation>> resultMap;

    public ViewerAnnotationRowMapper() {
        resultMap = new HashMap<Integer, List<ViewerAnnotation>>();
    }
    
    public Map<Integer, List<ViewerAnnotation>> getResultMap() {
        return this.resultMap;
    }

    @Override
    public ViewerAnnotation mapRow(ResultSet rs, int rowNum) throws SQLException {
        ViewerAnnotation viewerAnnotation = new ViewerAnnotation();

        Integer contentId = rs.getInt("content_id");
        viewerAnnotation.setAnnotationId(rs.getInt("annotation_id"));
        viewerAnnotation.setContentId(contentId);
        viewerAnnotation.setCaseId(rs.getInt("case_id"));
        viewerAnnotation.setText(rs.getString("text"));

        Date createdDate = rs.getDate("created");
        if (createdDate != null) {
            DateFormat dateFormat = new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601);
            String value = dateFormat.format(createdDate);
            viewerAnnotation.setDate(value);
        }

        if (resultMap.containsKey(contentId) == false) {
            List<ViewerAnnotation> annotations = new ArrayList<ViewerAnnotation>();
            annotations.add(viewerAnnotation);
            resultMap.put(contentId, annotations);
        } else {
            resultMap.get(contentId).add(viewerAnnotation);
        }
        
        return viewerAnnotation;
    }
    
}
