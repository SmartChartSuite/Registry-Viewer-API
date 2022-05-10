package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Annotation;
import io.swagger.model.Details;
import io.swagger.model.Value;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Content
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class Content   {
  @JsonProperty("contentId")
  private Integer contentId = null;

  @JsonProperty("section")
  private String section = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("question")
  private String question = null;

  @JsonProperty("date")
  private String date = null;

  @JsonProperty("derivedValue")
  private Value derivedValue = null;

  @JsonProperty("sourceValue")
  private Value sourceValue = null;

  @JsonProperty("flag")
  private String flag = null;

  @JsonProperty("annotation")
  @Valid
  private List<Annotation> annotation = null;

  @JsonProperty("details")
  private Details details = null;

  public Content contentId(Integer contentId) {
    this.contentId = contentId;
    return this;
  }

  /**
   * Get contentId
   * @return contentId
   **/
  @Schema(example = "1", description = "")
  
    public Integer getContentId() {
    return contentId;
  }

  public void setContentId(Integer contentId) {
    this.contentId = contentId;
  }

  public Content section(String section) {
    this.section = section;
    return this;
  }

  /**
   * Get section
   * @return section
   **/
  @Schema(example = "LabResults", description = "")
  
    public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public Content category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
   **/
  @Schema(example = "Syphilis", description = "")
  
    public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Content question(String question) {
    this.question = question;
    return this;
  }

  /**
   * Get question
   * @return question
   **/
  @Schema(example = "Lab Result", description = "")
  
    public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public Content date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   **/
  @Schema(example = "2022-01-14T05:00:00.000Z", description = "")
  
    public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Content derivedValue(Value derivedValue) {
    this.derivedValue = derivedValue;
    return this;
  }

  /**
   * Get derivedValue
   * @return derivedValue
   **/
  @Schema(description = "")
  
    @Valid
    public Value getDerivedValue() {
    return derivedValue;
  }

  public void setDerivedValue(Value derivedValue) {
    this.derivedValue = derivedValue;
  }

  public Content sourceValue(Value sourceValue) {
    this.sourceValue = sourceValue;
    return this;
  }

  /**
   * Get sourceValue
   * @return sourceValue
   **/
  @Schema(description = "")
  
    @Valid
    public Value getSourceValue() {
    return sourceValue;
  }

  public void setSourceValue(Value sourceValue) {
    this.sourceValue = sourceValue;
  }

  public Content flag(String flag) {
    this.flag = flag;
    return this;
  }

  /**
   * Get flag
   * @return flag
   **/
  @Schema(example = "flag1", description = "")
  
    public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public Content annotation(List<Annotation> annotation) {
    this.annotation = annotation;
    return this;
  }

  public Content addAnnotationItem(Annotation annotationItem) {
    if (this.annotation == null) {
      this.annotation = new ArrayList<Annotation>();
    }
    this.annotation.add(annotationItem);
    return this;
  }

  /**
   * Get annotation
   * @return annotation
   **/
  @Schema(description = "")
      @Valid
    public List<Annotation> getAnnotation() {
    return annotation;
  }

  public void setAnnotation(List<Annotation> annotation) {
    this.annotation = annotation;
  }

  public Content details(Details details) {
    this.details = details;
    return this;
  }

  /**
   * Get details
   * @return details
   **/
  @Schema(description = "")
  
    @Valid
    public Details getDetails() {
    return details;
  }

  public void setDetails(Details details) {
    this.details = details;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Content content = (Content) o;
    return Objects.equals(this.contentId, content.contentId) &&
        Objects.equals(this.section, content.section) &&
        Objects.equals(this.category, content.category) &&
        Objects.equals(this.question, content.question) &&
        Objects.equals(this.date, content.date) &&
        Objects.equals(this.derivedValue, content.derivedValue) &&
        Objects.equals(this.sourceValue, content.sourceValue) &&
        Objects.equals(this.flag, content.flag) &&
        Objects.equals(this.annotation, content.annotation) &&
        Objects.equals(this.details, content.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contentId, section, category, question, date, derivedValue, sourceValue, flag, annotation, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Content {\n");
    
    sb.append("    contentId: ").append(toIndentedString(contentId)).append("\n");
    sb.append("    section: ").append(toIndentedString(section)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    question: ").append(toIndentedString(question)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    derivedValue: ").append(toIndentedString(derivedValue)).append("\n");
    sb.append("    sourceValue: ").append(toIndentedString(sourceValue)).append("\n");
    sb.append("    flag: ").append(toIndentedString(flag)).append("\n");
    sb.append("    annotation: ").append(toIndentedString(annotation)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
