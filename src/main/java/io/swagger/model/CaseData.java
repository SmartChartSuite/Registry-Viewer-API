package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CaseData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class CaseData   {
  @JsonProperty("caseId")
  private Integer caseId = null;

  @JsonProperty("count")
  private Integer count = null;

  @JsonProperty("contents")
  @Valid
  private List<Content> contents = null;

  public CaseData caseId(Integer caseId) {
    this.caseId = caseId;
    return this;
  }

  /**
   * Get caseId
   * @return caseId
   **/
  @Schema(example = "1", description = "")
  
    public Integer getCaseId() {
    return caseId;
  }

  public void setCaseId(Integer caseId) {
    this.caseId = caseId;
  }

  public CaseData count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
   **/
  @Schema(example = "1", description = "")
  
    public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public CaseData contents(List<Content> contents) {
    this.contents = contents;
    return this;
  }

  public CaseData addContentsItem(Content contentsItem) {
    if (this.contents == null) {
      this.contents = new ArrayList<Content>();
    }
    this.contents.add(contentsItem);
    return this;
  }

  /**
   * Get contents
   * @return contents
   **/
  @Schema(description = "")
      @Valid
    public List<Content> getContents() {
    return contents;
  }

  public void setContents(List<Content> contents) {
    this.contents = contents;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaseData caseData = (CaseData) o;
    return Objects.equals(this.caseId, caseData.caseId) &&
        Objects.equals(this.count, caseData.count) &&
        Objects.equals(this.contents, caseData.contents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(caseId, count, contents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseData {\n");
    
    sb.append("    caseId: ").append(toIndentedString(caseId)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    contents: ").append(toIndentedString(contents)).append("\n");
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
