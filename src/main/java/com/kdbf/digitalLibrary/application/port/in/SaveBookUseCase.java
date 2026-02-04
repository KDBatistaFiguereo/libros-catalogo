package com.kdbf.digitalLibrary.application.port.in;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public interface SaveBookUseCase {

  public Book saveBook(SaveBookCommand command);
}
