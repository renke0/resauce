package com.renke.resauce.integration.matcher;

import com.renke.resauce.integration.object.DynamicObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "matching")
@Getter
public class PropertyMatchDescriptor {
  private final String expectedProperty;
  private final String actualProperty;

  private PropertyMatchDescriptor(String property) {
    expectedProperty = property;
    actualProperty = property;
  }

  public <T> T actual(DynamicObject dynamicObject) {
    return dynamicObject.nested(actualProperty);
  }

  public <T> T expected(DynamicObject dynamicObject) {
    return dynamicObject.nested(expectedProperty);
  }

  public static PropertyMatchDescriptor matching(String property) {
    return new PropertyMatchDescriptor(property);
  }
}
