package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DetailObservation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class DetailObservation  implements OneOfDetailsItems {
  @JsonProperty("date")
  private String date = null;

  @JsonProperty("system")
  private String system = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("display")
  private String display = null;

  @JsonProperty("value")
  private String value = null;

  @JsonProperty("unit")
  private String unit = null;

  @JsonProperty("tableDisplayText")
  private String tableDisplayText = null;

  public DetailObservation date(String date) {
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

  public DetailObservation system(String system) {
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

  public DetailObservation code(String code) {
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

  public DetailObservation display(String display) {
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

  public DetailObservation value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   **/
  @Schema(example = "Reactive", description = "")
  
    public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public DetailObservation unit(String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Get unit
   * @return unit
   **/
  @Schema(description = "")
  
    public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public DetailObservation tableDisplayText(String tableDisplayText) {
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
    DetailObservation detailObservation = (DetailObservation) o;
    return Objects.equals(this.date, detailObservation.date) &&
        Objects.equals(this.system, detailObservation.system) &&
        Objects.equals(this.code, detailObservation.code) &&
        Objects.equals(this.display, detailObservation.display) &&
        Objects.equals(this.value, detailObservation.value) &&
        Objects.equals(this.unit, detailObservation.unit) &&
        Objects.equals(this.tableDisplayText, detailObservation.tableDisplayText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, system, code, display, value, unit, tableDisplayText);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetailObservation {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    system: ").append(toIndentedString(system)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    display: ").append(toIndentedString(display)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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
