package com.renke.resauce.common.functions;

import java.util.function.Consumer;
import java.util.function.Function;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Functions {
  public static <T> Function<T, T> consuming(Consumer<T> consumer) {
    return t -> {
      consumer.accept(t);
      return t;
    };
  }
}
