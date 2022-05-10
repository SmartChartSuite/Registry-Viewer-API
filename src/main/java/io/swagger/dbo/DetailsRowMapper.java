package io.swagger.dbo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import org.springframework.jdbc.core.RowMapper;

import io.swagger.model.DetailCondition;
import io.swagger.model.DetailMeasurement;
import io.swagger.model.DetailMedication;
import io.swagger.model.DetailNote;
import io.swagger.model.DetailObservation;
import io.swagger.model.OneOfDetailsItems;

public class DetailsRowMapper implements RowMapper<OneOfDetailsItems> {
    private String entityType;
    private String shortDisplay;

    public DetailsRowMapper(Integer domainId) {
        if (domainId == 13L) {
            entityType = "drug_exposure";
        } else if (domainId == 19L) {
            entityType = "condition_occurrence";
        } else if (domainId == 27L) {
            entityType = "observation";
        } else if (domainId == 21L) {
            entityType = "measurement";
        } else if (domainId == 5085L) {
            entityType = "note";
        } else {
            entityType = null;
        }
    }

    public String getShortDisplay() {
        return this.shortDisplay;
    }

    public void setShortDisplay(String shortDisplay) {
        this.shortDisplay = shortDisplay;
    }

    @Override
    public OneOfDetailsItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        OneOfDetailsItems retVal = null;
        DateFormat dateFormat = new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601);

        if ("drug_exposure".equals(entityType)) {
            DetailMedication detailMed = new DetailMedication();
            Date startDate = rs.getDate("startDate");
            if (startDate != null) {
                String value = dateFormat.format(startDate);
                detailMed.setStartDate(value);
            }

            Date endDate = rs.getDate("endDate");
            if (endDate != null) {
                String value = dateFormat.format(endDate);
                detailMed.setStartDate(value);
            }

            detailMed.setSystem(rs.getString("System"));
            detailMed.setCode(rs.getString("Code"));
            detailMed.setDisplay(rs.getString("Display"));
            detailMed.setRefills(rs.getInt("Refills"));
            detailMed.setQuantity(rs.getInt("Quantity"));
            detailMed.setDaysSupply(rs.getInt("DaysSupply"));
            detailMed.setSig(rs.getString("Sig"));
            detailMed.setRouteSystem(rs.getString("RouteSystem"));
            detailMed.setRouteCode(rs.getString("RouteCode"));
            detailMed.setRouteDisplay(rs.getString("RouteDisplay"));
            detailMed.setLotNumber(rs.getString("LotNumber"));
            String sig = rs.getString("Sig");
            if (sig == null) sig = "";
            detailMed.setTableDisplayText((rs.getString("Display") + " " + sig).trim());

            retVal = detailMed;
        } else if ("condition_occurrence".equals(entityType)) {
            DetailCondition detailCondition = new DetailCondition();
            Date startDate = rs.getDate("startDate");
            if (startDate != null) {
                String value = dateFormat.format(startDate);
                detailCondition.setStartDate(value);
            }

            Date endDate = rs.getDate("endDate");
            if (endDate != null) {
                String value = dateFormat.format(endDate);
                detailCondition.setStartDate(value);
            }

            detailCondition.setSystem(rs.getString("System"));
            detailCondition.setCode(rs.getString("Code"));
            detailCondition.setDisplay(rs.getString("Display"));
            detailCondition.setTableDisplayText(rs.getString("Display"));

            retVal = detailCondition;
        } else if ("observation".equals(entityType)) {
            DetailObservation detailObservation = new DetailObservation();
            Date date = rs.getDate("Date");
            if (date != null) {
                String value = dateFormat.format(date);
                detailObservation.setDate(value);
            }

            detailObservation.setSystem(rs.getString("System"));
            detailObservation.setCode(rs.getString("Code"));
            detailObservation.setDisplay(rs.getString("Display"));
            
            String valueAsConceptCode = rs.getString("ValueAsConceptCode");
            Double valueAsNumber = rs.getDouble("ValueAsNumber");
            if (rs.wasNull())
                valueAsNumber = null;
            String valueAsString = rs.getString("ValueAsString");
            if (valueAsConceptCode != null && !valueAsConceptCode.isEmpty()) {
                detailObservation.setValue (rs.getString("ValueAsConceptDisplay"));
                detailObservation.setTableDisplayText (
                    rs.getString("Display") + " | " +
                    rs.getString("ValueAsConceptDisplay"));
            } else if (valueAsNumber != null) {
                detailObservation.setValue(String.valueOf(valueAsNumber));
                String tableDispText = rs.getString("Display") + " | " + String.valueOf(valueAsNumber);
                if (rs.getString("Unit") != null) {
                    tableDispText += " " + rs.getString("Unit");
                }
                detailObservation.setTableDisplayText(tableDispText);
            } else if (valueAsString != null && !valueAsString.isEmpty()) {
                detailObservation.setValue(valueAsString);
                detailObservation.setTableDisplayText(rs.getString("Display") + " | " + valueAsString);
            } else {
                detailObservation.setTableDisplayText(rs.getString("Display"));
            }

            if (rs.getString("Unit") != null)
                detailObservation.setUnit(rs.getString("Unit"));

            retVal = detailObservation;
        } else if ("measurement".equals(entityType)) {
            DetailMeasurement detailMeasurement = new DetailMeasurement();
            Date date = rs.getDate("Date");
            if (date != null) {
                String value = dateFormat.format(date);
                detailMeasurement.setDate(value);
            }

            detailMeasurement.setSystem(rs.getString("System"));
            detailMeasurement.setCode(rs.getString("Code"));
            detailMeasurement.setDisplay(rs.getString("Display"));
            
            String valueAsConceptCode = rs.getString("ValueAsConceptCode");
            Double valueAsNumber = rs.getDouble("ValueAsNumber");
            if (rs.wasNull()) {
                valueAsNumber = null;
            }
            if (valueAsConceptCode != null && !valueAsConceptCode.isEmpty()) {
                detailMeasurement.setValue(rs.getString("ValueAsConceptDisplay"));
                detailMeasurement.setTableDisplayText(
                    rs.getString("Display") + " | " + rs.getString("ValueAsConceptDisplay"));
            } else if (valueAsNumber != null) {
                String value = ((rs.getString("Operator")==null?"":rs.getString("Operator") + " ") + valueAsNumber).trim();
                detailMeasurement.setValue(value);
                detailMeasurement.setTableDisplayText(rs.getString("Display") + " | " + valueAsNumber);
            }  else {
                // check if we have something in the value_string_value column.
                String valueSourceValue = rs.getString("ValueSourceValue");
                if (valueSourceValue != null && !valueSourceValue.isEmpty()) {
                    detailMeasurement.setValue(valueSourceValue);
                    detailMeasurement.setTableDisplayText(rs.getString("Display") + " | " + valueSourceValue);
                } else {
                    detailMeasurement.setTableDisplayText(rs.getString("Display"));
                }
            }

            if (rs.getString("Unit") != null)
                detailMeasurement.setUnit(rs.getString("Unit"));

            int rangeLow = rs.getInt("RangeLow");
            if (!rs.wasNull())
                detailMeasurement.setRangeLow(rangeLow);
            int rangeHigh = rs.getInt("RangeHigh");
            if (!rs.wasNull())
                detailMeasurement.setRangeHigh(rangeHigh);
            retVal = detailMeasurement;
        } else if ("note".equals(entityType)) {
            DetailNote detailNote = new DetailNote();
            Date date = rs.getDate("Date");
            if (date != null) {
                String value = dateFormat.format(date);
                detailNote.setDate(value);
            }

            detailNote.setSystem(rs.getString("System"));
            detailNote.setCode(rs.getString("Code"));
            detailNote.setDisplay(rs.getString("Display"));
            detailNote.setNoteText(rs.getString("Value"));

            if (shortDisplay != null && !shortDisplay.isEmpty()) {
                detailNote.setTableDisplayText(shortDisplay);
            }

            retVal = detailNote;
        }

        return retVal;
    }
    
}
