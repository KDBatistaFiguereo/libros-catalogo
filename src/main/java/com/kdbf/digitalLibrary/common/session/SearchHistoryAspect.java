package com.kdbf.digitalLibrary.common.session;

import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

@Aspect
@Component
public class SearchHistoryAspect {

  private final BookSearchHistory searchHistory;

  public SearchHistoryAspect(BookSearchHistory searchHistory) {
    this.searchHistory = searchHistory;
  }

  @AfterReturning(pointcut = "execution(List<Book> com.kdbf.digitalLibrary.application.service." +
      "FindBooksByTitleService.findBooksByTitle(..))", returning = "books")
  public void registerHistory(List<Book> books) {
    searchHistory.addBooks(books);

  }

}
