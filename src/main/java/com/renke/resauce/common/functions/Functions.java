package com.renke.resauce.common.functions;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Functions {
  public static <T> Function<T, T> consuming(Consumer<T> consumer) {
    return t -> {
      consumer.accept(t);
      return t;
    };
  }

  public static <T, R> List<R> map(List<T> input, Function<T, R> function) {
    return input.stream().map(function).collect(Collectors.toList());
  }

  public static <T, R> Set<R> map(Set<T> input, Function<T, R> function) {
    return input.stream().map(function).collect(Collectors.toSet());
  }
}
