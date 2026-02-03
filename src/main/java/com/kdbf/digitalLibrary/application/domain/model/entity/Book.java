package com.kdbf.digitalLibrary.application.domain.model.entity;

import java.util.UUID;

import com.kdbf.digitalLibrary.application.domain.model.exception.NoTitleException;
import com.kdbf.digitalLibrary.application.domain.model.exception.NotValidAuthorException;

import lombok.Getter;

@Getter
public class Book {

  private UUID publicId;
  private String title;
  private Author author;
  private String language;
  private int downloads;

  @Default
  public Book(String title, Author author, String language, int downloads, UUID publicId) {

    title = validateTitle(title);

    if (author == null) {
      throw new NotValidAuthorException("An author can't be null.");
    }

    this.publicId = publicId;
    this.title = title;
    this.author = author;
    this.language = language;
    this.downloads = downloads;
  }

  public Book(String title, Author author, String language, int downloads) {
    this(title, author, language, downloads, null);
  }

  private String validateTitle(String title) {
    if (title == null) {
      throw new NoTitleException("A book's title cant be null.");
    }
    title = title.trim();
    if (title.isEmpty()) {
      throw new NoTitleException("A book's title cant be empty.");
    }
    return title;
  }
}
