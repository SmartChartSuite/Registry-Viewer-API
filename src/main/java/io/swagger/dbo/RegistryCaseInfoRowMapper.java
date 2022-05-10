package io.swagger.dbo;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.CaseInfo;

public class RegistryCaseInfoRowMapper implements RowMapper<CaseInfo> {
    public RegistryCaseInfoRowMapper() {
    }

    @Override
    public CaseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setPersonId(rs.getInt("PersonId"));

        return caseInfo;
    }
    
}
