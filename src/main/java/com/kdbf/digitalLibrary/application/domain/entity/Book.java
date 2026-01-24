package com.kdbf.digitalLibrary.application.domain.entity;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Book {

  private UUID publicid;
  private String title;
  private String author;
  private String language;
  private int downloads;

  public Book(String title, String author, String language, int downloads) {
    this.publicid = UUID.randomUUID();
    this.title = title;
    this.author = author;
    this.language = language;
    this.downloads = downloads;
  }
}
