package io.swagger.dbo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.Coding;
import io.swagger.model.Content;
import io.swagger.model.Value;

public class SCDAlgorithmRowMapper implements RowMapper<Content> {
    private static final Logger log = LoggerFactory.getLogger(SCDAlgorithmRowMapper.class);

    private Integer caseId;
    private Integer personId;

    public SCDAlgorithmRowMapper() {
    }
    
    public Integer getCaseId() {
        return this.caseId;
    }

    public Integer getPersonId() {
        return this.personId;
    }

    private Value constructValue(String system, String code, String display) {
        Value value = new Value();
        Coding coding = new Coding();
        coding.setSystem(system);
        coding.setCode(code);
        coding.setDisplay(display);
        value.setCoding(coding);

        return value;
    }

    @Override
    public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        Content content = new Content();

        content.setSection(rs.getString("section"));
        content.setCategory(rs.getString("category"));        
        content.setQuestion(rs.getString("question"));
        Date date = rs.getTimestamp("datetime");
        DateFormat dateFormat = new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        if (date != null) {
            String value = dateFormat.format(date);
            content.setDate(value);
        }

        Value value = constructValue(rs.getString("system"), rs.getString("code"), rs.getString("display"));
        content.setDerivedValue(value);

        return content;
    }
    
}
