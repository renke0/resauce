package com.renke.resauce;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public abstract class ResauceTest {
  protected static final int MIN_STRING_LENGTH = 5;
  protected static final int MAX_STRING_LENGTH = 15;
  protected static final int DEFAULT_COLLECTION_SIZE = 5;

  @Delegate
  protected final EasyRandom easyRandom = new EasyRandom(
      buildParameters());

  private EasyRandomParameters buildParameters() {
    var parameters = new EasyRandomParameters()
        .stringLengthRange(MIN_STRING_LENGTH, MAX_STRING_LENGTH)
        .collectionSizeRange(DEFAULT_COLLECTION_SIZE, DEFAULT_COLLECTION_SIZE)
        .overrideDefaultInitialization(true)
        .randomizationDepth(5);
    extraEasyRandomConfiguration(parameters);
    return parameters;
  }

  protected void extraEasyRandomConfiguration(EasyRandomParameters parameters) {
  }

  protected final <T> Set<T> nextSet(Class<T> type) {
    return nextSet(type, DEFAULT_COLLECTION_SIZE);
  }

  protected <T> Set<T> nextSet(Class<T> type, int count) {
    return IntStream.range(0, count)
        .mapToObj(i -> nextObject(type))
        .collect(Collectors.toSet());
  }

  protected final <T> T first(T[] array) {
    assertTrue(array.length > 0);
    return array[0];
  }

  protected final <T> T[] remaining(T[] array) {
    assertTrue(array.length > 0);
    return Arrays.copyOfRange(array, 1, array.length);
  }

  protected  <T, R> Set<R> map(Set<T> input, Function<T, R> function) {
    return input.stream().map(function).collect(Collectors.toSet());
  }

  @SneakyThrows
  protected final String json(Object object) {
    return new ObjectMapper().writeValueAsString(object);
  }
}
