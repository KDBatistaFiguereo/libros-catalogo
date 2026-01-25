package com.kdbf.digitalLibrary.application.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.kdbf.digitalLibrary.application.domain.exception.NotValidAuthorException;

public class AuthorTest {

  @Test
  public void shouldCreateAuthor() {
    String name = "Fyodor Dostoyevsky";
    Author author = new Author(name);

    assertEquals(name, author.getName());
  }

  public void shouldNotCreateAuthorWithEmptyName() {
    NotValidAuthorException exception = assertThrows(NotValidAuthorException.class, () -> {
      new Author("");
    });
    assertEquals("An author's name cant be empty.", exception.getMessage());
  }

  public void shouldNotCreateAuthorWithNullName() {
    NotValidAuthorException exception = assertThrows(NotValidAuthorException.class, () -> {
      new Author(null);
    });
    assertEquals("An author's name cant be null.", exception.getMessage());
  }
}
