package com.renke.resauce.integration.object;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;

public interface ObjectCreatorConfigurator {
  void configure(ObjectCreator objectCreator);

  static Predicate<Field> field(Class<?> type, String name) {
    return inClass(type).and(named(name));
  }
}
