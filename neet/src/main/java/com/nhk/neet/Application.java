package com.nhk.neet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class Application {
  public static void main(String... args) {
    SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
  }
}
