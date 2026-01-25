package com.kdbf.digitalLibrary.application.domain.model.entity;

import java.util.UUID;

import com.kdbf.digitalLibrary.application.domain.model.exception.NotValidAuthorException;

import lombok.Getter;

@Getter
public class Author {

  private UUID publicId;
  private String name;

  public Author(String name) {
    name = validateName(name);

    publicId = UUID.randomUUID();
    this.name = name;
  }

  private String validateName(String name) {
    if (name == null) {
      throw new NotValidAuthorException("An author's name cant be null.");
    }
    name = name.trim();
    if (name.isEmpty()) {
      throw new NotValidAuthorException("An author's name cant be empty.");
    }
    return name;
  }
}
