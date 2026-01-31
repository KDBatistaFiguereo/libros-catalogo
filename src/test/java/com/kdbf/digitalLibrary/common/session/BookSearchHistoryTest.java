package com.kdbf.digitalLibrary.common.session;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public class BookSearchHistoryTest {

  @Test
  public void shouldAddBooks() {

    List<Book> myBooks = List.of(
        new Book("Crime and punishment",
            new Author("Fyodor Dostoyevsky", null, null),
            "en",
            10),
        new Book("Don Quixote",
            new Author("Cervantes", null, null),
            "en",
            15));
    BookSearchHistory searchHistory = new BookSearchHistory();

    searchHistory.addBooks(myBooks);

    assertEquals(2, searchHistory.getBookHistory().size());
    assertEquals("Crime and punishment", searchHistory.getBookHistory().get(0).getTitle());
    assertEquals("Don Quixote", searchHistory.getBookHistory().get(1).getTitle());
  }

  @Test
  public void shouldNotAddDuplicates() {
    List<Book> myBooks = List.of(
        new Book("Crime and punishment",
            new Author("Fyodor Dostoyevsky", null, null),
            "en",
            10),
        new Book("Don Quixote",
            new Author("Cervantes", null, null),
            "en",
            15));
    BookSearchHistory searchHistory = new BookSearchHistory();

    searchHistory.addBooks(myBooks);
    searchHistory.addBooks(myBooks); // add the books again
                                     //
    assertEquals(2, searchHistory.getBookHistory().size());
  }

}
