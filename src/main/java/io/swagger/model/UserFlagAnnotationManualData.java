package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Annotation;
import io.swagger.model.ManualCaseData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserFlagAnnotationManualData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class UserFlagAnnotationManualData   {
  @JsonProperty("flag")
  private String flag = "Unknown";

  @JsonProperty("annotations")
  @Valid
  private List<Annotation> annotations = null;

  @JsonProperty("manualCaseData")
  @Valid
  private List<ManualCaseData> manualCaseData = null;

  public UserFlagAnnotationManualData flag(String flag) {
    this.flag = flag;
    return this;
  }

  /**
   * Get flag
   * @return flag
   **/
  @Schema(example = "Invalid Entry", description = "")
  
    public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public UserFlagAnnotationManualData annotations(List<Annotation> annotations) {
    this.annotations = annotations;
    return this;
  }

  public UserFlagAnnotationManualData addAnnotationsItem(Annotation annotationsItem) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<Annotation>();
    }
    this.annotations.add(annotationsItem);
    return this;
  }

  /**
   * Get annotations
   * @return annotations
   **/
  @Schema(description = "")
      @Valid
    public List<Annotation> getAnnotations() {
    return annotations;
  }

  public void setAnnotations(List<Annotation> annotations) {
    this.annotations = annotations;
  }

  public UserFlagAnnotationManualData manualCaseData(List<ManualCaseData> manualCaseData) {
    this.manualCaseData = manualCaseData;
    return this;
  }

  public UserFlagAnnotationManualData addManualCaseDataItem(ManualCaseData manualCaseDataItem) {
    if (this.manualCaseData == null) {
      this.manualCaseData = new ArrayList<ManualCaseData>();
    }
    this.manualCaseData.add(manualCaseDataItem);
    return this;
  }

  /**
   * Get manualCaseData
   * @return manualCaseData
   **/
  @Schema(description = "")
      @Valid
    public List<ManualCaseData> getManualCaseData() {
    return manualCaseData;
  }

  public void setManualCaseData(List<ManualCaseData> manualCaseData) {
    this.manualCaseData = manualCaseData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserFlagAnnotationManualData userFlagAnnotationManualData = (UserFlagAnnotationManualData) o;
    return Objects.equals(this.flag, userFlagAnnotationManualData.flag) &&
        Objects.equals(this.annotations, userFlagAnnotationManualData.annotations) &&
        Objects.equals(this.manualCaseData, userFlagAnnotationManualData.manualCaseData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flag, annotations, manualCaseData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserFlagAnnotationManualData {\n");
    
    sb.append("    flag: ").append(toIndentedString(flag)).append("\n");
    sb.append("    annotations: ").append(toIndentedString(annotations)).append("\n");
    sb.append("    manualCaseData: ").append(toIndentedString(manualCaseData)).append("\n");
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
