package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AuthResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-06T18:56:45.410Z[GMT]")


public class AuthResult   {
  @JsonProperty("sessionId")
  private String sessionId = null;

  @JsonProperty("expiresIfNoActivity")
  private Integer expiresIfNoActivity = null;

  public AuthResult sessionId(String sessionId) {
    this.sessionId = sessionId;
    return this;
  }

  /**
   * Get sessionId
   * @return sessionId
   **/
  @Schema(example = "session-id to use", description = "")
  
    public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public AuthResult expiresIfNoActivity(Integer expiresIfNoActivity) {
    this.expiresIfNoActivity = expiresIfNoActivity;
    return this;
  }

  /**
   * Get expiresIfNoActivity
   * @return expiresIfNoActivity
   **/
  @Schema(example = "5", description = "")
  
    public Integer getExpiresIfNoActivity() {
    return expiresIfNoActivity;
  }

  public void setExpiresIfNoActivity(Integer expiresIfNoActivity) {
    this.expiresIfNoActivity = expiresIfNoActivity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthResult authResult = (AuthResult) o;
    return Objects.equals(this.sessionId, authResult.sessionId) &&
        Objects.equals(this.expiresIfNoActivity, authResult.expiresIfNoActivity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionId, expiresIfNoActivity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthResult {\n");
    
    sb.append("    sessionId: ").append(toIndentedString(sessionId)).append("\n");
    sb.append("    expiresIfNoActivity: ").append(toIndentedString(expiresIfNoActivity)).append("\n");
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
