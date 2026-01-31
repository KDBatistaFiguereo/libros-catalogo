package com.kdbf.digitalLibrary.application.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.exception.NotValidAuthorException;

public class AuthorTest {

  @Test
  public void shouldCreateAuthor() {
    String name = "Fyodor Dostoyevsky";
    Author author = new Author(name, null, null);

    assertEquals(name, author.getName());
  }

  @Test
  public void shouldNotCreateAuthorWithEmptyName() {
    NotValidAuthorException exception = assertThrows(NotValidAuthorException.class, () -> {
      new Author("", null, null);
    });
    assertEquals("An author's name cant be empty.", exception.getMessage());
  }

  @Test
  public void shouldNotCreateAuthorWithNullName() {
    NotValidAuthorException exception = assertThrows(NotValidAuthorException.class, () -> {
      new Author(null, null, null);
    });
    assertEquals("An author's name cant be null.", exception.getMessage());
  }
}
