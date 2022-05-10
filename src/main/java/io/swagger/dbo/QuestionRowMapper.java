package io.swagger.dbo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.Question;

public class QuestionRowMapper implements RowMapper<Question> {
    private Map<Integer, Question> questionMap = new HashMap<Integer, Question> ();
    
    public Map<Integer, Question> getQuestionMap() {
        return this.questionMap;
    }

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setConceptId(rs.getInt("ConceptId"));
        question.setSection(rs.getString("Section"));
        question.setCategory(rs.getString("Category"));
        question.setText(rs.getString("Question"));

        questionMap.put(rs.getInt("ConceptId"), question);

        return question;
    }

}
