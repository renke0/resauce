package com.renke.resauce;

import java.util.Arrays;
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
      new EasyRandomParameters()
          .stringLengthRange(MIN_STRING_LENGTH, MAX_STRING_LENGTH)
          .collectionSizeRange(DEFAULT_COLLECTION_SIZE, DEFAULT_COLLECTION_SIZE)
          .overrideDefaultInitialization(true));

  protected final <T> T first(T[] array) {
    assertTrue(array.length > 0);
    return array[0];
  }

  protected final <T> T[] remaining(T[] array) {
    assertTrue(array.length > 0);
    return Arrays.copyOfRange(array, 1, array.length);
  }
}
