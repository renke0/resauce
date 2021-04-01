package com.renke.resauce.common.functions;

import com.renke.resauce.ResauceTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class FunctionsTest extends ResauceTest {

  @Nested
  @DisplayName("Function<T> consuming(Consumer<T>)")
  class ConsumingTest {

    @Test
    @DisplayName("Should accept the consumer")
    @SuppressWarnings("unchecked")
    void testConsuming() {
      var consumer = mock(Consumer.class);
      var actual = Functions.consuming(consumer).apply("hi");
      verify(consumer).accept("hi");
      assertEquals("hi", actual);
    }
  }
}
