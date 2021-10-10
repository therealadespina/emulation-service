package ru.adespina.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attrs {

  @JsonProperty("attrStr")
  private AttrStr attrStr;
}