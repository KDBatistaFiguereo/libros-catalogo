package com.kdbf.digitalLibrary.application.domain.service;

import org.springframework.stereotype.Service;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.port.in.SaveBookCommand;
import com.kdbf.digitalLibrary.application.port.in.SaveBookUseCase;
import com.kdbf.digitalLibrary.application.port.out.PersistBooksPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaveBookService implements SaveBookUseCase {

  private final PersistBooksPort persistPort;

  @Override
  public Book saveBook(SaveBookCommand command) {
    Author author = new Author(command.authorName(),
        command.birthYear(),
        command.deathYear());
    Book book = new Book(command.title(),
        author,
        command.title(),
        command.download());
    return persistPort.createBook(book);

  }

}
