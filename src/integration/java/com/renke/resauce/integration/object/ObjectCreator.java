package com.renke.resauce.integration.object;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class ObjectCreator implements InitializingBean {
  private final Map<String, Class<?>> aliases = new HashMap<>();
  private final Map<String, Consumer<?>> postProcessors = new HashMap<>();
  @Getter
  private final EasyRandomParameters parameters = new EasyRandomParameters();
  private final List<ObjectCreatorConfigurator> configurators;
  private final ObjectMapper objectMapper;
  private EasyRandom easyRandom;

  @Override
  public void afterPropertiesSet() {
    configurators.forEach(c -> c.configure(this));
    easyRandom = new EasyRandom(parameters);
  }

  public DynamicObject randomObject(String alias) {
    var type = Optional.ofNullable(aliases.get(alias)).orElseThrow();
    var bean = easyRandom.nextObject(type);
    postProcessor(alias).accept(bean);
    return objectMapper.convertValue(bean, DynamicObject.class);
  }

  ObjectCreator addAlias(String alias, Class<?> type) {
    aliases.put(alias, type);
    return this;
  }

  ObjectCreator addPostProcessor(String alias, Consumer<?> processor) {
    postProcessors.put(alias, processor);
    return this;
  }

  @SuppressWarnings("unchecked")
  private <T> Consumer<T> postProcessor(String objectAlias) {
    return (Consumer<T>) postProcessors.getOrDefault(objectAlias, n -> {
    });
  }
}
