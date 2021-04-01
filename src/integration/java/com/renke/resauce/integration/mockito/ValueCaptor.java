package com.renke.resauce.integration.mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ValueCaptor {

  private static final Map<String, List<Object>> captures = new HashMap<>();

  public static Matcher<Object> capture(String label) {
    return new BaseMatcher<>() {
      @Override
      public boolean matches(Object actual) {
        captures.computeIfAbsent(label, l -> new ArrayList<>()).add(actual);
        return true;
      }

      @Override
      public void describeTo(Description description) {
      }
    };
  }

  public static List<Object> getCaptures(String label) {
    return captures.getOrDefault(label, Collections.emptyList());
  }

  public static Object latestCapture(String label) {
    var captures = getCaptures(label);
    return captures.get(captures.size() - 1);
  }

  public static void resetCaptures() {
    captures.clear();
  }
}
