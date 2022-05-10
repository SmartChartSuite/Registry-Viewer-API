package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfDetailsItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = DetailCondition.class, name = "DetailCondition"),
  @JsonSubTypes.Type(value = DetailMedication.class, name = "DetailMedication"),
  @JsonSubTypes.Type(value = DetailObservation.class, name = "DetailObservation"),
  @JsonSubTypes.Type(value = DetailMeasurement.class, name = "DetailMeasurement"),
  @JsonSubTypes.Type(value = DetailNote.class, name = "DetailNote"),
  @JsonSubTypes.Type(value = DetailUserData.class, name = "DetailUserData")
})
public interface OneOfDetailsItems {

}
