package com.renke.resauce.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
import feign.codec.Decoder;
import feign.okhttp.OkHttpClient;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class OpenFeignConfiguration {
  @Bean
  Client client() {
    return new OkHttpClient();
  }

  @Bean
  public Decoder decoder(ObjectMapper objectMapper) {
    var jacksonConverter = new MappingJackson2HttpMessageConverter(objectMapper);
    return new ResponseEntityDecoder(new SpringDecoder(() -> new HttpMessageConverters(jacksonConverter)));
  }
}
