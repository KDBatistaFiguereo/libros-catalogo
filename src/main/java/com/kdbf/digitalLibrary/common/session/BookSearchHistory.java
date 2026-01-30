package com.kdbf.digitalLibrary.common.session;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class BookSearchHistory {
  private List<Book> bookHistory = new ArrayList<>();

  public List<Book> getBookHistory() {
    return Collections.unmodifiableList(bookHistory);
  }

  public void addBooks(List<Book> books) {
    List<Book> nonDuplicatedBooks = getNonDuplicatedBooks(books);
    if (nonDuplicatedBooks != null) {
      bookHistory.addAll(nonDuplicatedBooks);
    }
  }

  private List<Book> getNonDuplicatedBooks(List<Book> books) {
    List<Book> nonDuplicatedBooks = books.stream()
        .filter(x -> !bookHistory.contains(x))
        .toList();
    return nonDuplicatedBooks;
  }
}
