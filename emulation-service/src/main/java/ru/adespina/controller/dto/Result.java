package ru.adespina.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

  @JsonProperty("resultCode")
  private String resultCode;
}