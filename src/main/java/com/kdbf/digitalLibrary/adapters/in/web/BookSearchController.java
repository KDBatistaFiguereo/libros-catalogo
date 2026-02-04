package com.kdbf.digitalLibrary.adapters.in.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.domain.service.FindBooksByTitleService;
import com.kdbf.digitalLibrary.application.domain.service.SaveBookService;
import com.kdbf.digitalLibrary.application.port.in.FindBooksByTitleQuery;
import com.kdbf.digitalLibrary.application.port.in.SaveBookCommand;

@Controller
public class BookSearchController {

  private final FindBooksByTitleService findBooksService;
  private final SaveBookService saveBookService;

  public BookSearchController(FindBooksByTitleService findBooksService, SaveBookService saveBookService) {
    this.findBooksService = findBooksService;
    this.saveBookService = saveBookService;
  }

  @GetMapping("/libros/busqueda")
  public String searchBooks(@RequestParam String title, Model model) {
    FindBooksByTitleQuery query = new FindBooksByTitleQuery(title);
    List<Book> myBooks = findBooksService.findBooksByTitle(query);

    model.addAttribute("bookList", myBooks);

    return "book-search-result";

  }

  @PostMapping("/libros/guardar")
  public String saveBook(@ModelAttribute SaveBookCommand command,
      RedirectAttributes redirectAttributes) {
    saveBookService.saveBook(command);
    redirectAttributes.addAttribute("title", command.title());
    redirectAttributes.addFlashAttribute("message", "Book saved succesfully.");
    return "redirect:/libros/busqueda";

  }

}
