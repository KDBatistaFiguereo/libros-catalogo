package com.kdbf.digitalLibrary.common.session;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

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

  public List<Author> getAuthors() {
    return getBookHistory().stream()
        .map(x -> x.getAuthor())
        .distinct()
        .toList();
  }

  public List<Author> filterAuthorsByYear(Integer livingYear) {
    return getAuthors().stream()
        .filter(x -> (x.getBirthYear() != null))
        .filter(x -> x.getBirthYear().isBefore(Year.of(livingYear)) ||
            x.getBirthYear().equals(Year.of(livingYear)))
        .filter(x -> x.getDeathYear() == null ||
            x.getDeathYear().isAfter(Year.of(livingYear)) ||
            x.getDeathYear().equals(Year.of(livingYear)))
        .toList();
  }
}
