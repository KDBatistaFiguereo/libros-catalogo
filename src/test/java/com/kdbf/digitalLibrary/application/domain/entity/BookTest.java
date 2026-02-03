package com.kdbf.digitalLibrary.application.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.domain.model.exception.NoTitleException;
import com.kdbf.digitalLibrary.application.domain.model.exception.NotValidAuthorException;

public class BookTest {

  @Test
  public void shouldCreateBookNormally() {
    String title = "Crime and Punishment";
    Author author = new Author("Fyodor Dostoyevsky", null, null);
    String language = "EN";
    int downloads = 10;
    Book myBook = new Book(title, author, language, downloads);

    assertEquals(title, myBook.getTitle());
    assertEquals(author, myBook.getAuthor());
    assertEquals(language, myBook.getLanguage());
    assertEquals(downloads, myBook.getDownloads());
  }

  @Test
  public void shouldNotCreateBookWithEmptyTitle() {
    NoTitleException exception = assertThrows(NoTitleException.class, () -> {
      Book myBook = new Book("", new Author("Anonymous", null, null), "", 0);
    });

    assertEquals("A book's title cant be empty.", exception.getMessage());
  }

  @Test
  public void shouldNotCreateBookWithNullTitle() {
    NoTitleException exception = assertThrows(NoTitleException.class, () -> {
      Book myBook = new Book(null, new Author("Anonymous", null, null), "", 0);
    });

    assertEquals("A book's title cant be null.", exception.getMessage());
  }

  @Test
  public void shouldNotCreateBookWithNullAuthor() {
    NotValidAuthorException exception = assertThrows(NotValidAuthorException.class, () -> {
      Book myBook = new Book("Crime and Punishment", null, "", 0);
    });

    assertEquals("An author can't be null.", exception.getMessage());
  }
}
