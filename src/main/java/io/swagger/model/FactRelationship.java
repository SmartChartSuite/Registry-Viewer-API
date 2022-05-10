package io.swagger.model;

public class FactRelationship {
    private Integer domainConceptId1;
    private Integer factId1;
    private Integer domainConceptId2;
    private Integer factId2;
    private Integer relationshipConceptId;

    public FactRelationship() {
    }

    public Integer getDomainConceptId1() {
        return this.domainConceptId1;
    }

    public void setDomainConceptId1(Integer domainConceptId1) {
        this.domainConceptId1 = domainConceptId1;
    }

    public Integer getFactId1() {
        return this.factId1;
    }

    public void setFactId1(Integer factId1) {
        this.factId1 = factId1;
    }

    public Integer getDomainConceptId2() {
        return this.domainConceptId2;
    }

    public void setDomainConceptId2(Integer domainConceptId2) {
        this.domainConceptId2 = domainConceptId2;
    }

    public Integer getFactId2() {
        return this.factId2;
    }

    public void setFactId2(Integer factId2) {
        this.factId2 = factId2;
    }

    public Integer getRelationshipConceptId() {
        return this.relationshipConceptId;
    }

    public void setRelationshipConceptId(Integer relationshipConceptId) {
        this.relationshipConceptId = relationshipConceptId;
    }
}