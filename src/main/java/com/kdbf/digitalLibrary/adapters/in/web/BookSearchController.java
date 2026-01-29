package com.kdbf.digitalLibrary.adapters.in.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.domain.service.FindBooksByTitleService;
import com.kdbf.digitalLibrary.application.port.in.FindBooksByTitleQuery;

@Controller
public class BookSearchController {

  private final FindBooksByTitleService findBooksService;

  public BookSearchController(FindBooksByTitleService findBooksService) {
    this.findBooksService = findBooksService;
  }

  @GetMapping("/libros/busqueda")
  public String searchBooks(@RequestParam String title, Model model) {
    FindBooksByTitleQuery query = new FindBooksByTitleQuery(title);
    List<Book> myBooks = findBooksService.findBooksByTitle(query);

    model.addAttribute("bookList", myBooks);

    return "book-search-result";

  }

}
