package com.kdbf.digitalLibrary.adapters.in.web;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.common.session.BookSearchHistory;

@WebMvcTest
public class BookHistoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private BookSearchHistory bookSearchHistory;

  public void shouldReturnBookHistory() throws Exception {
    when(bookSearchHistory.getBookHistory()).thenReturn(List.of(
        new Book("The Strange case of Dr Jekyll and Mr Hyde",
            new Author("Stevenson, Robert Louis"),
            "en",
            10)));
    mockMvc.perform(get("/libros/historial"))
        .andExpect(status().isOk())
        .andExpect(view().name("book-history"))
        .andExpect(model().attributeExists("historyList"))
        .andExpect(model().attribute("historyList", hasSize(2)));

  }

}
