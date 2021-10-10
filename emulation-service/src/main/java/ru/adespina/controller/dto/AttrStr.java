package ru.adespina.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttrStr {

  @JsonProperty("key")
  private String key;
  @JsonProperty("value")
  private String value;
}