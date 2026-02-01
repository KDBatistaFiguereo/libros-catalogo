package com.kdbf.digitalLibrary.common.session;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.port.in.FindBooksByTitleQuery;
import com.kdbf.digitalLibrary.application.port.in.FindBooksByTitleUseCase;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class SearchHistoryAspectTest {

  @Autowired
  private FindBooksByTitleUseCase findBooksByTitleUseCase;

  @Autowired
  private BookSearchHistory bookSearchHistory;

  @Test
  public void shouldRegisterHistoryWhenBooksFound() {
    FindBooksByTitleQuery query = new FindBooksByTitleQuery("test");
    List<Book> myBooks = findBooksByTitleUseCase.findBooksByTitle(query);

    assertEquals(myBooks.size(), bookSearchHistory.getBookHistory().size());
  }

}
