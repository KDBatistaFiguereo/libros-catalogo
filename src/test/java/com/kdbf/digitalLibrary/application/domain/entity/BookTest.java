package com.kdbf.digitalLibrary.application.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class BookTest {

  @Test
  public void shouldCreateBookNormally() {
    String title = "Crime and Punishment";
    String author = "Fyodor Dostoyevsky";
    String language = "EN";
    int downloads = 10;
    Book myBook = new Book(title, author, language, downloads);

    assertNotNull(myBook.getPublicId());
    assertEquals(title, myBook.getTitle());
    assertEquals(author, myBook.getAuthor());
    assertEquals(language, myBook.getLanguage());
    assertEquals(downloads, myBook.getDownloads());
  }

}
