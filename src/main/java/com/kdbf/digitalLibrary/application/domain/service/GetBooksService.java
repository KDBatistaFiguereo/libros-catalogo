package com.kdbf.digitalLibrary.application.domain.service;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.port.out.LoadBooksPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetBooksService {
  private final LoadBooksPort loadBooks;

  public List<Book> getAllBooks() {
    return loadBooks.findAll();
  }

  public List<Author> getAuthors() {
    return getAllBooks().stream()
        .map(x -> x.getAuthor())
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

  public List<Book> filterSavedBooksLang(String language) {
    return getAllBooks().stream()
        .filter(x -> x.getLanguage().equals(language))
        .toList();
  }
}
