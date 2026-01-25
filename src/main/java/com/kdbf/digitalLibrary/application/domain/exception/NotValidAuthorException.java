package com.kdbf.digitalLibrary.application.domain.exception;

public class NotValidAuthorException extends RuntimeException {
  public NotValidAuthorException(String message) {
    super(message);
  }
}
