package ru.adespina.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Folder {

  @JsonProperty("template")
  private String template;
  @JsonProperty("id")
  private String id;
  @JsonProperty("target")
  private String target;
  @JsonProperty("class")
  private String clasz;
  @JsonProperty("attrs")
  private List<Attrs> attrs;
  @JsonProperty("result")
  private Result result;
}