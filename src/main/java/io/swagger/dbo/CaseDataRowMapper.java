package io.swagger.dbo;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.Coding;
import io.swagger.model.Content;
import io.swagger.model.Question;
import io.swagger.model.Value;

public class CaseDataRowMapper implements RowMapper<Content> {
    private Integer caseId;
    private Integer personId;
    private Map<Integer, Question> questionMap;

    public CaseDataRowMapper() {
    }
    
    public CaseDataRowMapper(Map<Integer, Question> questionMap) {
        this.questionMap = questionMap;
    }

    public Integer getCaseId() {
        return this.caseId;
    }

    public Integer getPersonId() {
        return this.personId;
    }

    public void setCategoryConceptMap(Map<Integer, Question> questionMap) {
        this.questionMap = questionMap;
    }

    private String ratioInteger(String value) {
        String[] values = value.split(":");
        String numeratorString = "";
        String denominatorString = "";
        if (values.length == 2) {
            try {
                double numerator = Double.parseDouble(values[0]);
                double denominator = Double.parseDouble(values[1]);

                if (numerator/((int)numerator) == 1) {
                    numeratorString = String.valueOf((int)numerator);
                }

                if (denominator/((int)denominator) == 1) {
                    denominatorString = String.valueOf((int)denominator);
                }
            } catch (NumberFormatException nfe) {
                return value;
            }

            return numeratorString + ":" + denominatorString;
        } else {
            return value;
        }
    }

    private Value constructValue(String[] derivedValues) {
        Value value = new Value();
        if (derivedValues.length == 3) {
            Coding coding = new Coding();
            coding.setSystem(derivedValues[0]);
            coding.setCode(derivedValues[1]);
            coding.setDisplay(derivedValues[2]);
            value.setCoding(coding);
        } else if (derivedValues.length > 3) {
            Coding coding = new Coding();
            coding.setSystem(derivedValues[1]);
            coding.setCode(derivedValues[2]);
            coding.setDisplay(derivedValues[3]);
            value.setCoding(coding);
        } else {
            value.setValue(ratioInteger(derivedValues[0]));
        }

        if (derivedValues.length >= 5) {
            value.setValue(ratioInteger(derivedValues[4]));
            if (derivedValues.length == 6)
                value.setUnit(derivedValues[5]);
        }

        return value;
    }

    @Override
    public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Get section and category from conceptId
        Question question = questionMap.get(rs.getInt("ObservationConceptId"));
        if ("Demographics".equals(question.getSection())) {
            return null;
        }

        Content content = new Content();
        content.setSection(question.getSection());
        content.setCategory(question.getCategory());        

        content.setContentId(rs.getInt("ObservationId"));
        content.setQuestion(rs.getString("ObservationConceptName"));

        Date date = rs.getDate("Date");
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601);
            String value = dateFormat.format(date);

            content.setDate(value);
        }

        // get derived value. Format defined in
        // https://github.gatech.edu/HDAP/SmartPacer-RC-API-FORMS/wiki/RC-API-Client 
        String obsValue = rs.getString("DerivedValue");
        String[] derivedValues = obsValue.split("\\^");
        if (derivedValues.length > 3) {
            // first one is timestamp. Override the date in the observation.date.
            content.setDate(derivedValues[0]);
        }

        Value value = constructValue(derivedValues);
        content.setDerivedValue(value);

        obsValue = rs.getString("SourceValue");
        derivedValues = obsValue.split("\\^");
        value = constructValue(derivedValues);
        content.setSourceValue(value);

        return content;
    }
    
}
