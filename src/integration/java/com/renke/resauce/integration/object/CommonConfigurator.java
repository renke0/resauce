package com.renke.resauce.integration.object;

import org.springframework.stereotype.Component;

@Component
public class CommonConfigurator implements ObjectCreatorConfigurator {
  @Override
  public void configure(ObjectCreator objectCreator) {
    objectCreator.getParameters()
        .stringLengthRange(5, 15)
        .collectionSizeRange(5, 5)
        .overrideDefaultInitialization(true);
  }
}
