package com.renke.resauce.configuration;

import com.renke.resauce.model.GameMetadata;
import com.renke.resauce.service.GameMetadataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetadataConfiguration {
  @Bean
  GameMetadata gameMetadata(GameMetadataService gameMetadataService) {
    return gameMetadataService.loadGameMetadata();
  }
}
