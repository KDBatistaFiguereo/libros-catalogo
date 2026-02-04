package com.kdbf.digitalLibrary.application.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
}
