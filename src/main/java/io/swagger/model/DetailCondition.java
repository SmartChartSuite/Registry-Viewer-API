package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DetailCondition
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class DetailCondition  implements OneOfDetailsItems {
  @JsonProperty("startDate")
  private String startDate = null;

  @JsonProperty("endDate")
  private String endDate = null;

  @JsonProperty("system")
  private String system = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("display")
  private String display = null;

  @JsonProperty("tableDisplayText")
  private String tableDisplayText = null;

  public DetailCondition startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
   **/
  @Schema(example = "2022-01-14T05:00:00.000Z", description = "")
  
    public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public DetailCondition endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
   **/
  @Schema(example = "2022-01-14T05:00:00.000Z", description = "")
  
    public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public DetailCondition system(String system) {
    this.system = system;
    return this;
  }

  /**
   * Get system
   * @return system
   **/
  @Schema(example = "LOINC", description = "")
  
    public String getSystem() {
    return system;
  }

  public void setSystem(String system) {
    this.system = system;
  }

  public DetailCondition code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   **/
  @Schema(example = "20507-0", description = "")
  
    public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public DetailCondition display(String display) {
    this.display = display;
    return this;
  }

  /**
   * Get display
   * @return display
   **/
  @Schema(example = "Reagin Ab [Presence] in Serum by RPR", description = "")
  
    public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public DetailCondition tableDisplayText(String tableDisplayText) {
    this.tableDisplayText = tableDisplayText;
    return this;
  }

  /**
   * Get tableDisplayText
   * @return tableDisplayText
   **/
  @Schema(example = "Reagin Ab [Presence] in Serum by RPR | value", description = "")
  
    public String getTableDisplayText() {
    return tableDisplayText;
  }

  public void setTableDisplayText(String tableDisplayText) {
    this.tableDisplayText = tableDisplayText;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DetailCondition detailCondition = (DetailCondition) o;
    return Objects.equals(this.startDate, detailCondition.startDate) &&
        Objects.equals(this.endDate, detailCondition.endDate) &&
        Objects.equals(this.system, detailCondition.system) &&
        Objects.equals(this.code, detailCondition.code) &&
        Objects.equals(this.display, detailCondition.display) &&
        Objects.equals(this.tableDisplayText, detailCondition.tableDisplayText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate, system, code, display, tableDisplayText);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetailCondition {\n");
    
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    system: ").append(toIndentedString(system)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    display: ").append(toIndentedString(display)).append("\n");
    sb.append("    tableDisplayText: ").append(toIndentedString(tableDisplayText)).append("\n");
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
