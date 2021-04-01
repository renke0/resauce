package com.renke.resauce;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@NoArgsConstructor
public class ResauceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ResauceApplication.class, args);
  }
}
