package com.kdbf.digitalLibrary.application.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.port.in.FindBooksByTitleQuery;
import com.kdbf.digitalLibrary.application.port.in.FindBooksByTitleUseCase;
import com.kdbf.digitalLibrary.application.port.out.FindBooksPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FindBooksByTitleService implements FindBooksByTitleUseCase {

  private final FindBooksPort findBooksPort;

  @Override
  public List<Book> findBooksByTitle(FindBooksByTitleQuery query) {
    return findBooksPort.findBooksByTitle(query.title());
  }

}
