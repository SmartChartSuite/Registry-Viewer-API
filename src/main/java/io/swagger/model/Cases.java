package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.ModelCase;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Cases
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class Cases   {
  @JsonProperty("count")
  private Integer count = null;

  @JsonProperty("cases")
  @Valid
  private List<ModelCase> cases = null;

  public Cases count(Integer count) {
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

  public Cases cases(List<ModelCase> cases) {
    this.cases = cases;
    return this;
  }

  public Cases addCasesItem(ModelCase casesItem) {
    if (this.cases == null) {
      this.cases = new ArrayList<ModelCase>();
    }
    this.cases.add(casesItem);
    return this;
  }

  /**
   * Get cases
   * @return cases
   **/
  @Schema(description = "")
      @Valid
    public List<ModelCase> getCases() {
    return cases;
  }

  public void setCases(List<ModelCase> cases) {
    this.cases = cases;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cases cases = (Cases) o;
    return Objects.equals(this.count, cases.count) &&
        Objects.equals(this.cases, cases.cases);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, cases);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cases {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    cases: ").append(toIndentedString(cases)).append("\n");
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
