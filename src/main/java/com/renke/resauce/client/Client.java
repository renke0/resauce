package com.renke.resauce.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@FeignClient
public @interface Client {
  @AliasFor(annotation = Component.class)
  String value() default "";

  @AliasFor(annotation = FeignClient.class)
  String url();

  @AliasFor(annotation = FeignClient.class)
  String name();
}
