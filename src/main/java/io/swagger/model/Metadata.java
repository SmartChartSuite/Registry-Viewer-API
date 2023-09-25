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


public class Metadata   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("tag")
  private String tag = null;

  @JsonProperty("viewerConfig")
  private JsonNode viewerConfig = null;

  public Metadata name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name of registry
   * @return name
   **/
  @Schema(example = "Syphilis Registry", description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Metadata description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "Registry description", description = "")
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Metadata tag(String tag) {
    this.tag = tag;
    return this;
  }

  /**
   * Get tag
   * @return tag
   **/
  @Schema(example = "Registry tag name", description = "")
  
    public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public Metadata viewerConfig(JsonNode viewerConfig) {
    this.viewerConfig = viewerConfig;
    return this;
  }
 
  /**
   * Get viewerConfig
   * @return viewerConfig
   **/
  @Schema(example = "Registry Viewer UI Config", description = "")
  
    public JsonNode getViewerConfig() {
    return viewerConfig;
  }

  public void setViewerConfig(JsonNode viewerConfig) {
    this.viewerConfig = viewerConfig;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadata metaData = (Metadata) o;
    return Objects.equals(this.name, metaData.name) &&
        Objects.equals(this.description, metaData.description) &&
        Objects.equals(this.tag, metaData.tag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, tag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Metadata {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
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
