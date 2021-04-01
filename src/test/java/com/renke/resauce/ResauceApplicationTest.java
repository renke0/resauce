package com.renke.resauce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class ResauceApplicationTest extends ResauceTest {
  @Nested
  @DisplayName("void main(String[])")
  class MainTest {

    @Test
    @DisplayName("should initialize a Spring application with the main application class")
    void testMain() {
      try (var springApplication = mockStatic(SpringApplication.class)) {
        springApplication.when(() -> SpringApplication.run(any(Class.class), any(String[].class))).thenReturn(null);
        ResauceApplication.main(new String[0]);
        springApplication.verify(() -> SpringApplication.run(eq(ResauceApplication.class), any(String[].class)));
      }
    }
  }
}
