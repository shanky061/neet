package com.nhk.neet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

  @GetMapping("/")
  public String getInfo() {
    return "Nippon Hikikomori Kyokai";
  }
}
