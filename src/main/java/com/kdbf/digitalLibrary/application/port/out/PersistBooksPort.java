package com.kdbf.digitalLibrary.application.port.out;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public interface PersistBooksPort {
  void save(Book book);
}
