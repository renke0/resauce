package com.renke.resauce.common.parsing;

public class ParsingException extends RuntimeException {
  public ParsingException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
