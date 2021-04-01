package com.renke.resauce.integration.object;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliteratorUnknownSize;

public class DynamicObjectDeserializer extends StdDeserializer<DynamicObject> {
  protected DynamicObjectDeserializer() {
    super((Class<?>) null);
  }

  @Override
  public DynamicObject deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    ObjectNode node = codec.readTree(parser);
    return deserialize(node);
  }

  private DynamicObject deserialize(JsonNode node) {
    var contents = stream(node.fields())
        .map(this::deserializeEntry)
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    return new DynamicObject(contents);
  }

  private <T> Stream<T> stream(Iterator<T> iterator) {
    return StreamSupport.stream(spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
  }

  private Entry<String, Object> deserializeEntry(Entry<String, JsonNode> entry) {
    if (entry.getValue().isValueNode()) {
      return new SimpleEntry<>(entry.getKey(), asValue((ValueNode) entry.getValue()));
    }
    if (entry.getValue().isObject()) {
      return new SimpleEntry<>(entry.getKey(), deserialize(entry.getValue()));
    }
    if (entry.getValue().isArray()) {
      return new SimpleEntry<>(
          entry.getKey(),
          stream(entry.getValue().elements())
              .map(this::deserialize)
              .collect(Collectors.toList()));
    }
    throw new IllegalStateException();
  }

  private Object asValue(ValueNode value) {
    return new ObjectMapper().convertValue(value, Object.class);
  }
}
