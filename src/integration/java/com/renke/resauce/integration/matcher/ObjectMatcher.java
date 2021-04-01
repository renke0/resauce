package com.renke.resauce.integration.matcher;

import com.renke.resauce.integration.object.DynamicObject;
import java.util.Map;
import org.hamcrest.BaseMatcher;

public abstract class ObjectMatcher extends BaseMatcher<DynamicObject> {

  @Override
  @SuppressWarnings("unchecked")
  public final boolean matches(Object actual) {
    return actual instanceof Map && objectMatches(new DynamicObject((Map<String, Object>) actual));
  }

  public abstract boolean objectMatches(DynamicObject actual);

}
