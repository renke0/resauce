package com.renke.resauce.integration.object;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class DynamicObjectModule extends SimpleModule {
  public DynamicObjectModule() {
    addDeserializer(DynamicObject.class, new DynamicObjectDeserializer());
  }
}
