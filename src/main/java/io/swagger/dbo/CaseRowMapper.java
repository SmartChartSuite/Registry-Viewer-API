package io.swagger.dbo;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.ModelCase;

public class CaseRowMapper implements RowMapper<ModelCase> {

    @Override
    public ModelCase mapRow(ResultSet rs, int rowNum) throws SQLException {
        ModelCase modelCase = new ModelCase();

        modelCase.setCaseId(rs.getInt("CaseId"));

        Date initialReportDate = rs.getDate("InitialRecordDate");
        DateFormat dateFormat = new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601);
        String initialReportDateString = dateFormat.format(initialReportDate);
        modelCase.setInitialReportDate(initialReportDateString);
        
        modelCase.setLastName(rs.getString("LastName")==null?"":rs.getString("LastName"));
        String first1 = rs.getString("FirstName1");
        String first2 = rs.getString("FirstName2");
        String firstname = "";
        if (first1 == null) {
            if (first2 != null) 
                firstname = first2.trim();
        } else {
            if (first2 == null) 
                firstname = first1.trim();
            else
                firstname = first1.trim() + " " + first2.trim();
        }
        modelCase.setFirstName(firstname);
        modelCase.setGender(rs.getString("Gender")==null?"":rs.getString("Gender"));
        
        int dobYear = rs.getInt("DobYear");
        int dobMonth = rs.getInt("DobMonth");
        int dobDay = rs.getInt("DobDay");

        String dobString = "";
        if (dobYear != 0) {
            dobString += String.format("%04d" , dobYear);
        } else {
            dobString += "1970";
        }

        if (dobMonth != 0) {
            dobString += "-" + String.format("%02d" , dobMonth);
        } else {
            dobString += "-01";
        }

        if (dobDay != 0) {
            dobString += "-" + String.format("%02d" , dobDay);
        } else {
            dobString += "-01";
        }

        modelCase.setDob(dobString);

        String contact1 = rs.getString("Contact1");
        String contact2 = rs.getString("Contact2");
        String contact3 = rs.getString("Contact3");
        String phone = "";

        if (contact1 != null && !contact1.isEmpty()) {
            String[] contactInfo = contact1.split(":");
            if (contactInfo.length == 3) {
                if ("PHONE".equalsIgnoreCase(contactInfo[0])) {
                    phone = contactInfo[2];
                }
            }
        }

        if (phone.isEmpty() && contact2 != null && !contact2.isEmpty()) {
            String[] contactInfo = contact2.split(":");
            if (contactInfo.length == 3) {
                if ("PHONE".equalsIgnoreCase(contactInfo[0])) {
                    phone = contactInfo[2];
                }
            }
        }

        if (phone.isEmpty() && contact3 != null && !contact3.isEmpty()) {
            String[] contactInfo = contact3.split(":");
            if (contactInfo.length == 3) {
                if ("PHONE".equalsIgnoreCase(contactInfo[0])) {
                    phone = contactInfo[2];
                }        
            }
        }

        modelCase.setPhone(phone);

        String address1 = rs.getString("Address1");
        String address2 = rs.getString("Address2");
        String street = "";
        if (address1 == null) {
            if (address2 != null) {
                street = address2.trim();
            }
        } else {
            if (first2 == null) {
                street = address1.trim();
            } else {
                street = address1.trim() + " " + address2.trim();
            }
        }

        modelCase.setStreet(street);
        modelCase.setCity(rs.getString("City")==null?"":rs.getString("City"));
        modelCase.setState(rs.getString("State")==null?"":rs.getString("State"));
        modelCase.setZip(rs.getString("Zip")==null?"":rs.getString("Zip"));

        modelCase.setStatus(rs.getString("Status")==null?"":rs.getString("Status"));

        return modelCase;
    }
    
}
