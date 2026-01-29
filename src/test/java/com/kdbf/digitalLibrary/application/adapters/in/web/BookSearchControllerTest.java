package com.kdbf.digitalLibrary.application.adapters.in.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.kdbf.digitalLibrary.adapters.in.web.BookSearchController;
import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.domain.service.FindBooksByTitleService;
import com.kdbf.digitalLibrary.application.port.in.FindBooksByTitleQuery;

@WebMvcTest(BookSearchController.class)
public class BookSearchControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private FindBooksByTitleService findByTitleService;

  @Test
  public void shouldReturnListOfBooks() throws Exception {
    FindBooksByTitleQuery query = new FindBooksByTitleQuery("strange");
    when(findByTitleService.findBooksByTitle(query)).thenReturn(
        List.of(
            new Book("The Strange case of Dr Jekyll and Mr Hyde",
                new Author("Stevenson, Robert Louis"),
                "en",
                10),
            new Book("The Strange case of Dr Jekyll and Mr Hyde",
                new Author("Stevenson, Robert Louis"),
                "en",
                20)));
    mockMvc.perform(get("/libros/busqueda").param("title", "strange"))
        .andExpect(status().isOk())
        .andExpect(view().name("book-search-result"))
        .andExpect(model().attributeExists("bookList"))
        .andExpect(model().attribute("bookList", hasSize(2)))
        .andExpect(model().attribute("bookList", hasItem(
            allOf(
                hasProperty("title", is("The Strange case of Dr Jekyll and Mr Hyde")),
                hasProperty("author", hasProperty("name", is("Stevenson, Robert Louis"))),
                hasProperty("language", is("en")),
                hasProperty("downloads", is(10))))));

  }

}
