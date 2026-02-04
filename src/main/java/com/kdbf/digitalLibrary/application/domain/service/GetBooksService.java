package com.kdbf.digitalLibrary.application.domain.service;

import java.util.List;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.port.out.LoadBooksPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetBooksService {
  private final LoadBooksPort loadBooks;

  public List<Book> getAllBooks() {
    return loadBooks.findAll();
  }
}
