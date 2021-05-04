package com.renke.resauce.configuration;

import com.renke.resauce.ResauceApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class IOConfiguration {
  @Bean
  ResourcePatternResolver resourcePatternResolver() {
    return new PathMatchingResourcePatternResolver(ResauceApplication.class.getClassLoader());
  }
}
