package com.urun.counttool.controller;

import com.urun.counttool.model.entity.ResultDto;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
   public Map<String,Object> map = new HashMap<>();
   public ResultDto resultDto = new ResultDto();

  public ResultDto getResultDto() {
    return resultDto;
  }

  public void setResultDto(ResultDto resultDto) {
    this.resultDto = resultDto;
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }
}
