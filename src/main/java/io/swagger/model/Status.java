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
  @JsonProperty("severity")
  private String severity = null;

  @JsonProperty("lastUpdated")
  private String lastUpdated = null;

  @JsonProperty("information")
  private String information = null;

  public Status name(String severity) {
    this.severity = severity;
    return this;
  }

  /**
   * Get name of registry
   * @return name
   **/
  @Schema(example = "active", description = "The case is in active query")
  
    public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public Status lastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "2011-12-03:00:00:00", description = "Last Updated Datetime")
  
  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Status information(String information) {
    this.information = information;
    return this;
  }

  /**
   * Get tag
   * @return tag
   **/
  @Schema(example = "", description = "Some information about the severity.")
  
    public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
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
    return Objects.equals(this.severity, metaData.severity) &&
        Objects.equals(this.lastUpdated, metaData.lastUpdated) &&
        Objects.equals(this.information, metaData.information);
  }

  @Override
  public int hashCode() {
    return Objects.hash(severity, lastUpdated, information);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Metadata {\n");
    
    sb.append("    severity: ").append(toIndentedString(severity)).append("\n");
    sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
    sb.append("    information: ").append(toIndentedString(information)).append("\n");
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
