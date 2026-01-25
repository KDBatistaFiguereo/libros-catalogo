package com.kdbf.digitalLibrary.application.domain.entity;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Author {

  private UUID publicId;
  private String name;

  public Author(String name) {
    publicId = UUID.randomUUID();
    this.name = name;
  }
}
