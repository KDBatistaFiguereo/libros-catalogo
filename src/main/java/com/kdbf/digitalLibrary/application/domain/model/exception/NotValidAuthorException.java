package com.kdbf.digitalLibrary.application.domain.model.exception;

public class NotValidAuthorException extends RuntimeException {
  public NotValidAuthorException(String message) {
    super(message);
  }
}
