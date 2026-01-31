package com.kdbf.digitalLibrary.application.domain.model.entity;

import java.time.Year;
import java.util.UUID;

import com.kdbf.digitalLibrary.application.domain.model.exception.NotValidAuthorException;

import lombok.Getter;

@Getter
public class Author {

  private UUID publicId;
  private String name;
  private Year birthYear;
  private Year deathYear;

  public Author(String name, Year birthYear, Year deathYear) {
    name = validateName(name);
    publicId = UUID.randomUUID();
    this.name = name;
    this.birthYear = birthYear;
    this.deathYear = deathYear;
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
