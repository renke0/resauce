package com.renke.resauce.configuration;

import com.renke.resauce.model.base.VersionTypeModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
  private final List<Converter<?, ?>> converters;

  @Override
  protected void addFormatters(FormatterRegistry registry) {
    converters.forEach(registry::addConverter);
  }

  @Component
  static class StringToVersionTypeModelConverter implements Converter<String, VersionTypeModel> {
    @Override
    public VersionTypeModel convert(String source) {
      return VersionTypeModel.fromValue(source);
    }
  }
}
