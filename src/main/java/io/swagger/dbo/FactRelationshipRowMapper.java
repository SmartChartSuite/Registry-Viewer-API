package io.swagger.dbo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.FactRelationship;

public class FactRelationshipRowMapper implements RowMapper<FactRelationship> {

    @Override
    public FactRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
        FactRelationship factRelationship = new FactRelationship();

        factRelationship.setDomainConceptId1(rs.getInt("domain_concept_id_1"));
        factRelationship.setFactId1(rs.getInt("fact_id_1"));
        factRelationship.setDomainConceptId2(rs.getInt("domain_concept_id_2"));
        factRelationship.setFactId2(rs.getInt("fact_id_2"));
        
        return factRelationship;
    }
    
}
