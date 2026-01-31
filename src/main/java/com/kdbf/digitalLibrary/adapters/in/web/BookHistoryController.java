package com.kdbf.digitalLibrary.adapters.in.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.common.session.BookSearchHistory;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BookHistoryController {

  private final BookSearchHistory bookSearchHistory;

  @GetMapping("/libros/historial")
  public String seeHistory(Model model) {
    List<Book> bookHistory = bookSearchHistory.getBookHistory();
    model.addAttribute("historyList", bookHistory);
    return "book-history";
  }

}
