package com.kdbf.digitalLibrary.adapters.in.web;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.common.session.BookSearchHistory;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private BookSearchHistory bookSearchHistory;

  @Test
  public void shouldReturnPreviousAuthors() throws Exception {
    when(bookSearchHistory.getAuthors()).thenReturn(List.of(
        new Author("Stevenson, Robert Louis",
            Year.of(1850),
            Year.of(1894))));

    mockMvc.perform(get("/libros/autores"))
        .andExpect(status().isOk())
        .andExpect(view().name("authors"))
        .andExpect(model().attributeExists("authorList"))
        .andExpect(model().attribute("authorList", hasSize(1)));
  }

}
