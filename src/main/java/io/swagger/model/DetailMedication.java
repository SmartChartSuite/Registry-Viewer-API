package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DetailMedication
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class DetailMedication  implements OneOfDetailsItems {
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

  @JsonProperty("refills")
  private Integer refills = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("daysSupply")
  private Integer daysSupply = null;

  @JsonProperty("sig")
  private String sig = null;

  @JsonProperty("route_system")
  private String routeSystem = null;

  @JsonProperty("route_code")
  private String routeCode = null;

  @JsonProperty("route_display")
  private String routeDisplay = null;

  @JsonProperty("lotNumber")
  private String lotNumber = null;

  @JsonProperty("tableDisplayText")
  private String tableDisplayText = null;

  public DetailMedication startDate(String startDate) {
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

  public DetailMedication endDate(String endDate) {
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

  public DetailMedication system(String system) {
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

  public DetailMedication code(String code) {
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

  public DetailMedication display(String display) {
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

  public DetailMedication refills(Integer refills) {
    this.refills = refills;
    return this;
  }

  /**
   * Get refills
   * @return refills
   **/
  @Schema(example = "2", description = "")
  
    public Integer getRefills() {
    return refills;
  }

  public void setRefills(Integer refills) {
    this.refills = refills;
  }

  public DetailMedication quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
   **/
  @Schema(example = "5", description = "")
  
    public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public DetailMedication daysSupply(Integer daysSupply) {
    this.daysSupply = daysSupply;
    return this;
  }

  /**
   * Get daysSupply
   * @return daysSupply
   **/
  @Schema(example = "30", description = "")
  
    public Integer getDaysSupply() {
    return daysSupply;
  }

  public void setDaysSupply(Integer daysSupply) {
    this.daysSupply = daysSupply;
  }

  public DetailMedication sig(String sig) {
    this.sig = sig;
    return this;
  }

  /**
   * Get sig
   * @return sig
   **/
  @Schema(description = "")
  
    public String getSig() {
    return sig;
  }

  public void setSig(String sig) {
    this.sig = sig;
  }

  public DetailMedication routeSystem(String routeSystem) {
    this.routeSystem = routeSystem;
    return this;
  }

  /**
   * Get routeSystem
   * @return routeSystem
   **/
  @Schema(description = "")
  
    public String getRouteSystem() {
    return routeSystem;
  }

  public void setRouteSystem(String routeSystem) {
    this.routeSystem = routeSystem;
  }

  public DetailMedication routeCode(String routeCode) {
    this.routeCode = routeCode;
    return this;
  }

  /**
   * Get routeCode
   * @return routeCode
   **/
  @Schema(example = "1234-5", description = "")
  
    public String getRouteCode() {
    return routeCode;
  }

  public void setRouteCode(String routeCode) {
    this.routeCode = routeCode;
  }

  public DetailMedication routeDisplay(String routeDisplay) {
    this.routeDisplay = routeDisplay;
    return this;
  }

  /**
   * Get routeDisplay
   * @return routeDisplay
   **/
  @Schema(example = "route text info", description = "")
  
    public String getRouteDisplay() {
    return routeDisplay;
  }

  public void setRouteDisplay(String routeDisplay) {
    this.routeDisplay = routeDisplay;
  }

  public DetailMedication lotNumber(String lotNumber) {
    this.lotNumber = lotNumber;
    return this;
  }

  /**
   * Get lotNumber
   * @return lotNumber
   **/
  @Schema(example = "lot number", description = "")
  
    public String getLotNumber() {
    return lotNumber;
  }

  public void setLotNumber(String lotNumber) {
    this.lotNumber = lotNumber;
  }

  public DetailMedication tableDisplayText(String tableDisplayText) {
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
    DetailMedication detailMedication = (DetailMedication) o;
    return Objects.equals(this.startDate, detailMedication.startDate) &&
        Objects.equals(this.endDate, detailMedication.endDate) &&
        Objects.equals(this.system, detailMedication.system) &&
        Objects.equals(this.code, detailMedication.code) &&
        Objects.equals(this.display, detailMedication.display) &&
        Objects.equals(this.refills, detailMedication.refills) &&
        Objects.equals(this.quantity, detailMedication.quantity) &&
        Objects.equals(this.daysSupply, detailMedication.daysSupply) &&
        Objects.equals(this.sig, detailMedication.sig) &&
        Objects.equals(this.routeSystem, detailMedication.routeSystem) &&
        Objects.equals(this.routeCode, detailMedication.routeCode) &&
        Objects.equals(this.routeDisplay, detailMedication.routeDisplay) &&
        Objects.equals(this.lotNumber, detailMedication.lotNumber) &&
        Objects.equals(this.tableDisplayText, detailMedication.tableDisplayText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate, system, code, display, refills, quantity, daysSupply, sig, routeSystem, routeCode, routeDisplay, lotNumber, tableDisplayText);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetailMedication {\n");
    
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    system: ").append(toIndentedString(system)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    display: ").append(toIndentedString(display)).append("\n");
    sb.append("    refills: ").append(toIndentedString(refills)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    daysSupply: ").append(toIndentedString(daysSupply)).append("\n");
    sb.append("    sig: ").append(toIndentedString(sig)).append("\n");
    sb.append("    routeSystem: ").append(toIndentedString(routeSystem)).append("\n");
    sb.append("    routeCode: ").append(toIndentedString(routeCode)).append("\n");
    sb.append("    routeDisplay: ").append(toIndentedString(routeDisplay)).append("\n");
    sb.append("    lotNumber: ").append(toIndentedString(lotNumber)).append("\n");
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
