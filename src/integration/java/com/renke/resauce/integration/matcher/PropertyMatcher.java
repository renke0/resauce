package com.renke.resauce.integration.matcher;

import com.renke.resauce.integration.object.DynamicObject;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.hamcrest.Description;

import static org.hamcrest.Matchers.equalTo;

@RequiredArgsConstructor
@Builder
public class PropertyMatcher extends ObjectMatcher {
  private final DynamicObject expectedObject;
  @Singular
  private final List<PropertyMatchDescriptor> descriptors;

  @Override
  public void describeTo(Description description) {
  }

  @Override
  public boolean objectMatches(DynamicObject actualObject) {
    return descriptors.stream()
        .allMatch(descriptor -> equalTo(descriptor.expected(expectedObject))
            .matches(descriptor.actual(actualObject)));
  }
}
