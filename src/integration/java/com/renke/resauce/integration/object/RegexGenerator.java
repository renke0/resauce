package com.renke.resauce.integration.object;

import com.github.curiousoddman.rgxgen.RgxGen;
import com.github.curiousoddman.rgxgen.config.RgxGenProperties;
import org.jeasy.random.api.Randomizer;

import static com.github.curiousoddman.rgxgen.config.RgxGenOption.INFINITE_PATTERN_REPETITION;

public class RegexGenerator implements Randomizer<String> {
  public static final String VERSION_ID_REGEX = "(\\d{1,2}\\.\\d{1,2}\\.\\d{1,2})|(\\d{2}w\\d{2}[a-z])";
  public static final String URL_REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
  public static final String HEXA_REGEX = "[A-F0-9]*";

  private final static int MAXIMUM_PATTERN_REPETITION = 30;
  private final RgxGenProperties properties;
  private final String regex;

  public RegexGenerator(String regex) {
    this.regex = regex;
    properties = new RgxGenProperties();
    INFINITE_PATTERN_REPETITION.setInProperties(properties, MAXIMUM_PATTERN_REPETITION);
  }

  @Override
  public String getRandomValue() {
    var rgxGen = new RgxGen(regex);
    rgxGen.setProperties(properties);
    return rgxGen.generate();
  }
}
