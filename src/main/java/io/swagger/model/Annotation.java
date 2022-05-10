package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Annotation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class Annotation   {
  @JsonProperty("annotationId")
  private Integer annotationId = null;

  @JsonProperty("text")
  private String text = null;

  @JsonProperty("date")
  private String date = null;

  public Annotation annotationId(Integer annotationId) {
    this.annotationId = annotationId;
    return this;
  }

  /**
   * Get annotationId
   * @return annotationId
   **/
  @Schema(example = "1", description = "")
  
    public Integer getAnnotationId() {
    return annotationId;
  }

  public void setAnnotationId(Integer annotationId) {
    this.annotationId = annotationId;
  }

  public Annotation text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
   **/
  @Schema(example = "will be delted if annotationId is valid but text is empty", description = "")
  
    public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Annotation date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   **/
  @Schema(example = "2012-12-12", description = "")
  
    public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Annotation annotation = (Annotation) o;
    return Objects.equals(this.annotationId, annotation.annotationId) &&
        Objects.equals(this.text, annotation.text) &&
        Objects.equals(this.date, annotation.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotationId, text, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Annotation {\n");
    
    sb.append("    annotationId: ").append(toIndentedString(annotationId)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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
