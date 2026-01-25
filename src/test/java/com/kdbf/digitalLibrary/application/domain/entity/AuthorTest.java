package com.kdbf.digitalLibrary.application.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AuthorTest {

  @Test
  public void shouldCreateAuthor() {
    String name = "Fyodor Dostoyevsky";
    Author author = new Author(name);

    assertEquals(name, author.getName());
  }
}
