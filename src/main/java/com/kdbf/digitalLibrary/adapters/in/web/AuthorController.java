package com.kdbf.digitalLibrary.adapters.in.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.common.session.BookSearchHistory;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AuthorController {
  private final BookSearchHistory bookHistory;

  @GetMapping("/libros/autores")
  public String showAuthors(@RequestParam(required = false) Integer livingYear, Model model) {
    List<Author> authors;
    if (livingYear == null) {
      authors = bookHistory.getAuthors();
    } else {
      authors = bookHistory.filterAuthorsByYear(livingYear);
    }

    model.addAttribute("authorList", authors);
    return "authors";
  }

}
