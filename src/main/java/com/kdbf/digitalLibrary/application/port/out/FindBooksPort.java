package com.kdbf.digitalLibrary.application.port.out;

import java.util.List;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public interface FindBooksPort {
  List<Book> findBooksByTitle(String title);
}
