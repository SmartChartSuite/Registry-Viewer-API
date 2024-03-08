package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * CaseData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class Status   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("detail")
  private String detail = null;

  @JsonProperty("createdDateTime")
  private String createdDateTime = null;

  @JsonProperty("activatedDateTime")
  private String activatedDateTime = null;

  @JsonProperty("lastSuccessfulDateTime")
  private String lastSuccessfulDateTime = null;

  @JsonProperty("nextScheduledDateTime")
  private String nextScheduledDateTime = null;

  @JsonProperty("caseStartedRunningDateTime")
  private String caseStartedRunningDateTime = null;


  public Status code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code of registry status
   * @return code
   **/
  @Schema(example = "information", description = "Status code in Registry for User")
  
    public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Status detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Get detail of registry status code
   * @return detail
   **/
  @Schema(example = "Case is running in normal mode.", description = "Detail description of status.")
  
    public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  /**
   * 
   * @param createdDateTime
   * @return
   */
  public Status createdDateTime(String createdDateTime) {
    this.createdDateTime = createdDateTime;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "2011-12-03:00:00:00", description = "Date and time that case is created")
  
  public String getCreatedDateTime() {
    return createdDateTime;
  }

  public void setCreatedDateTime(String createdDateTime) {
    this.createdDateTime = createdDateTime;
  }

   /**
   * 
   * @param activatedDateTime
   * @return
   */
  public Status activatedDateTime(String activatedDateTime) {
    this.activatedDateTime = activatedDateTime;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "2011-12-03:00:00:00", description = "Date and time that case is running (activated)")
  
  public String getActivatedDateTime() {
    return activatedDateTime;
  }

  public void setActivatedDateTime(String activatedDateTime) {
    this.activatedDateTime = activatedDateTime;
  }
 
   /**
   * 
   * @param lastSuccessfulDateTime
   * @return
   */
  public Status lastSuccessfulDateTime(String lastSuccessfulDateTime) {
    this.lastSuccessfulDateTime = lastSuccessfulDateTime;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "2011-12-03:00:00:00", description = "Date and time that was last successful")
  
  public String getLastSuccessfulDateTime() {
    return lastSuccessfulDateTime;
  }

  public void setLastSuccessfulDateTime(String lastSuccessfulDateTime) {
    this.lastSuccessfulDateTime = lastSuccessfulDateTime;
  }

   /**
   * 
   * @param nextScheduledDateTime
   * @return
   */
  public Status nextScheduledDateTime(String nextScheduledDateTime) {
    this.nextScheduledDateTime = nextScheduledDateTime;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "2011-12-03:00:00:00", description = "Next scheduled date and time")
  
  public String getNextScheduledDateTime() {
    return nextScheduledDateTime;
  }

  public void setNextScheduledDateTime(String nextScheduledDateTime) {
    this.nextScheduledDateTime = nextScheduledDateTime;
  }
 
 
   /**
   * 
   * @param caseStartedRunningDateTime
   * @return
   */
  public Status caseStartedRunningDateTime(String caseStartedRunningDateTime) {
    this.caseStartedRunningDateTime = caseStartedRunningDateTime;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "2011-12-03:00:00:00", description = "Date and time when case was started running")
  
  public String getCaseStartedRunningDateTime() {
    return caseStartedRunningDateTime;
  }

  public void setcaseStartedRunningDateTime(String caseStartedRunningDateTime) {
    this.caseStartedRunningDateTime = caseStartedRunningDateTime;
  }
 
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Status metaData = (Status) o;
    return Objects.equals(this.code, metaData.code) &&
        Objects.equals(this.detail, metaData.detail) &&
        Objects.equals(this.createdDateTime, metaData.createdDateTime) &&
        Objects.equals(this.activatedDateTime, metaData.activatedDateTime) &&
        Objects.equals(this.lastSuccessfulDateTime, metaData.lastSuccessfulDateTime) &&
        Objects.equals(this.nextScheduledDateTime, metaData.nextScheduledDateTime) &&
        Objects.equals(this.caseStartedRunningDateTime, metaData.caseStartedRunningDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, detail, createdDateTime, activatedDateTime, lastSuccessfulDateTime, nextScheduledDateTime, caseStartedRunningDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Metadata {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    createdDateTime: ").append(toIndentedString(createdDateTime)).append("\n");
    sb.append("    activatedDateTime: ").append(toIndentedString(activatedDateTime)).append("\n");
    sb.append("    lastSuccessfulDateTime: ").append(toIndentedString(lastSuccessfulDateTime)).append("\n");
    sb.append("    nextScheduledDateTime: ").append(toIndentedString(nextScheduledDateTime)).append("\n");
    sb.append("    caseStartedRunningDateTime: ").append(toIndentedString(caseStartedRunningDateTime)).append("\n");
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
