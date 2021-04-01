package com.renke.resauce.integration.object;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.experimental.Delegate;

import static com.google.common.base.Preconditions.checkState;

public class DynamicObject implements Map<String, Object> {

  @Delegate
  private final Map<String, Object> contents;

  public DynamicObject(Map<String, Object> contents) {
    this.contents = createContentMap(contents);
  }

  public DynamicObject with(String name, Object value) {
    var copy = new HashMap<>(contents);
    copy.put(name, value);
    return new DynamicObject(copy);
  }

  public DynamicObject without(String name) {
    var copy = new HashMap<>(contents);
    copy.remove(name);
    return new DynamicObject(copy);
  }

  public <T> T get(String name, Class<T> type) {
    return type.cast(get(name));
  }

  @SuppressWarnings("unchecked")
  public <T> T nested(String name) {
    var chunks = name.split("\\.");
    Object current = contents;
    var regex = Pattern.compile("^([^\\[]+)\\[(\\d+)]$");
    for (var chunk : chunks) {
      var matcher = regex.matcher(chunk);
      if (matcher.find()) {
        var property = matcher.group(1);
        var index = Integer.parseInt(matcher.group(2));
        current = get(current, property);
        current = getIndex(current, index);
      } else {
        current = get(current, chunk);
      }
    }
    return (T) current;
  }

  private Map<String, Object> createContentMap(Map<String, Object> contents) {
    return contents.entrySet()
        .stream()
        .map(entry -> new SimpleEntry<>(entry.getKey(), resolveValue(entry.getValue())))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }

  @SuppressWarnings("unchecked")
  private <T> T resolveValue(Object value) {
    if (value instanceof Map) {
      return (T) new DynamicObject((Map<String, Object>) value);
    }
    if (value instanceof List) {
      return (T) ((List<?>) value).stream()
          .map(this::resolveValue)
          .collect(Collectors.toList());
    }
    return (T) value;
  }

  @SuppressWarnings("unchecked")
  private <T> T get(Object container, String property) {
    checkState(container instanceof Map, "Cannot get property");
    return ((Map<?, T>) container).get(property);
  }

  @SuppressWarnings("unchecked")
  private <T> T getIndex(Object container, int index) {
    checkState(container instanceof List, "Cannot get index");
    return (T) ((List<?>) container).get(index);
  }
}
