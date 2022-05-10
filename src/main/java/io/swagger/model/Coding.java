package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Coding
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class Coding   {
  @JsonProperty("system")
  private String system = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("display")
  private String display = null;

  public Coding system(String system) {
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

  public Coding code(String code) {
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

  public Coding display(String display) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coding coding = (Coding) o;
    return Objects.equals(this.system, coding.system) &&
        Objects.equals(this.code, coding.code) &&
        Objects.equals(this.display, coding.display);
  }

  @Override
  public int hashCode() {
    return Objects.hash(system, code, display);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Coding {\n");
    
    sb.append("    system: ").append(toIndentedString(system)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    display: ").append(toIndentedString(display)).append("\n");
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
