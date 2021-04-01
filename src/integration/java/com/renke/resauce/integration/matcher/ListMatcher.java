package com.renke.resauce.integration.matcher;

import com.renke.resauce.integration.object.DynamicObject;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public abstract class ListMatcher extends BaseMatcher<List<DynamicObject>> {

  @Override
  @SuppressWarnings("unchecked")
  public final boolean matches(Object actual) {
    return actual instanceof List
        && this.listMatches(
        ((List<?>) actual).stream()
            .map(m -> new DynamicObject((Map<String, Object>) m))
            .collect(Collectors.toList()));
  }

  public abstract boolean listMatches(List<DynamicObject> actual);

  public static Matcher<?> allMatch(Predicate<DynamicObject> latestPredicate) {
    return new ListMatcher() {
      @Override
      public boolean listMatches(List<DynamicObject> actual) {
        return actual.stream().allMatch(latestPredicate);
      }

      @Override
      public void describeTo(Description description) {

      }
    };
  }
}
