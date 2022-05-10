package io.swagger.model;

public class ViewerFlag {
    private Integer contentId;
    private String flag;
    private Integer caseId;

    public ViewerFlag() {

    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentId() {
        return this.contentId;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getCaseId() {
        return this.caseId;
    }
}
