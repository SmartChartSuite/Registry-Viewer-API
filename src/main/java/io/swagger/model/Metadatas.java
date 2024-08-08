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


public class Metadatas   {
  @JsonProperty("version")
  private String version = null;

  @JsonProperty("count")
  private Integer count = null;

  @JsonProperty("metadatas")
  @Valid
  private List<Metadata> metadatas = null;

  public Metadatas count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get version
   * @return version
   **/
  @Schema(example = "1.0.0", description = "")
  
    public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
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

  public Metadatas metadatas(List<Metadata> metadatas) {
    this.metadatas = metadatas;
    return this;
  }

  public Metadatas addMetadataItem(Metadata metadataItem) {
    if (this.metadatas == null) {
      this.metadatas = new ArrayList<Metadata>();
    }
    this.metadatas.add(metadataItem);
    return this;
  }

  public Metadata findMetadataItem(String tag) {
    if (this.metadatas == null) {
      return null;
    }

    for (Metadata metadata : this.metadatas) {
      if (tag.equalsIgnoreCase(metadata.getTag())) {
        return metadata;
      }
    }

    return null;
  }

  /**
   * Get cases
   * @return cases
   **/
  @Schema(description = "")
  @Valid
  public List<Metadata> getMetadatas() {
    return metadatas;
  }

  public void setMetadatas(List<Metadata> metadatas) {
    this.metadatas = metadatas;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadatas cases = (Metadatas) o;
    return Objects.equals(this.count, cases.count) &&
        Objects.equals(this.metadatas, cases.metadatas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, metadatas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cases {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    metadatas: ").append(toIndentedString(metadatas)).append("\n");
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
