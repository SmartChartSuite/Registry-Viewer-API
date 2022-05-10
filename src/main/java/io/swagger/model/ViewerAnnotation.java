package io.swagger.model;

public class ViewerAnnotation {
    private Integer annotationId;
    private Integer contentId;
    private String text;
    private Integer caseId;
    private String date;

    public ViewerAnnotation() {

    }

    public void setAnnotationId(Integer annotationId) {
        this.annotationId = annotationId;
    }

    public Integer getAnnotationId() {
        return this.annotationId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentId() {
        return this.contentId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getCaseId() {
        return this.caseId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }
}
