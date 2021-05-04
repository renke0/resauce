package com.renke.resauce.configuration;

import com.renke.resauce.ResauceTest;
import com.renke.resauce.configuration.WebMvcConfiguration.StringToVersionTypeModelConverter;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

import static com.renke.resauce.model.base.VersionTypeModel.OLD_ALPHA;
import static com.renke.resauce.model.base.VersionTypeModel.OLD_BETA;
import static com.renke.resauce.model.base.VersionTypeModel.RELEASE;
import static com.renke.resauce.model.base.VersionTypeModel.SNAPSHOT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class WebMvcConfigurationTest extends ResauceTest {
  @Spy
  @SuppressWarnings("unchecked")
  List<Converter<?, ?>> converters = List.of(mock(Converter.class), mock(Converter.class), mock(Converter.class));
  @InjectMocks
  WebMvcConfiguration configuration;

  private static Stream<Arguments> converterTestProvider() {
    return Stream.of(
        Arguments.of(new StringToVersionTypeModelConverter(), "snapshot", SNAPSHOT),
        Arguments.of(new StringToVersionTypeModelConverter(), "release", RELEASE),
        Arguments.of(new StringToVersionTypeModelConverter(), "old_alpha", OLD_ALPHA),
        Arguments.of(new StringToVersionTypeModelConverter(), "old_beta", OLD_BETA)
    );
  }

  @Nested
  @DisplayName("addFormatters")
  class AddFormattersTest {
    @Test
    @DisplayName("Should add all the given converters")
    void testAddFormatters() {
      var registry = mock(FormatterRegistry.class);
      configuration.addFormatters(registry);
      verify(registry, times(3)).addConverter(any(Converter.class));
    }
  }

  @ParameterizedTest(name = "Should convert {1} into {2}")
  @MethodSource("converterTestProvider")
  <I, E, C extends Converter<I, E>> void convertTest(C converter, I input, E expected) {
    var actual = converter.convert(input);
    assertEquals(expected, actual);
  }
}
