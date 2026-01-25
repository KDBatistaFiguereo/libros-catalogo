package com.kdbf.digitalLibrary.application.port.in;

import java.util.List;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public interface FindBooksByTitleUseCase {
  public List<Book> findBooksByTitle(FindBooksByTitleQuery query);
}
