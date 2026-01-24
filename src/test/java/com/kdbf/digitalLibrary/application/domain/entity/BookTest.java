package com.kdbf.digitalLibrary.application.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BookTest {

  @Test
  public void shouldCreateBookNormally() {
    String title = "Crime and Punishment";
    String author = "Fyodor Dostoyevsky";
    String language = "EN";
    int downloads = 10;
    Book myBook = new Book(title, author, language, downloads);

    assertNotNull(myBook.getPublicid());
    assertEquals(title, myBook.getTitle());
    assertEquals(author, myBook.getAuthor());
    assertEquals(language, myBook.getLanguage());
    assertEquals(downloads, myBook.getDownloads());
  }

  @Test
  public void shouldNotCreateBookWithEmptyTitle() {
    NoTitleException exception = assertThrows(NoTitleException.class, () -> {
      Book myBook = new Book("", "Anonymous", null, null);
    })
  }

  @Test
  public void shouldNotCreateBookWithNullTitle(){
    NoTitleException exception = assertThrows(NoTitleException.class, () -> {
      Book myBook = new Book(null, "Anonymous", null, null);
    })
  }
}
