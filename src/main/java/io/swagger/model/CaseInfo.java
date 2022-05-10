package io.swagger.model;

public class CaseInfo {
    private Integer caseInfoId;
    private Integer personId;
    private String patientIdentifier;

    public CaseInfo() {

    }

    public void setCaseInfoId(Integer caseInfoId) {
        this.caseInfoId = caseInfoId;
    }

    public Integer getCaseInfoId() {
        return this.caseInfoId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return this.personId;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public String getPatientIdentifier() {
        return this.patientIdentifier;
    }
}
