package ru.adespina.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Failure {

  @JsonProperty("errorCode")
  private String errorCode;
  @JsonProperty("errorReason")
  private String errorReason;
}