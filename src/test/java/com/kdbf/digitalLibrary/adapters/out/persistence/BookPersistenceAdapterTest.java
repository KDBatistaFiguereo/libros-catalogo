package com.kdbf.digitalLibrary.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BookPersistenceAdapterTest {

  @Autowired
  private BookPersistenceAdapter bookPersistenceAdapter;

  @Test
  @Transactional
  void shouldCreateBookWithNewAuthor() {
    Author author = new Author("Stevenson, Robert Louis", null, null);
    Book book = new Book("Treasure island", author, "en", 10);

    Book savedBook = bookPersistenceAdapter.createBook(book);

    assertThat(savedBook.getPublicId()).as("Public ID should be generated/populated").isNotNull();
    assertThat(savedBook.getTitle()).isEqualTo("Treasure island");
    assertThat(savedBook.getAuthor().getName()).isEqualTo("Stevenson, Robert Louis");

    Optional<Book> retrieved = bookPersistenceAdapter.findByPublicId(savedBook.getPublicId());

    assertThat(retrieved)
        .as("Book should be findable by public ID: " + savedBook.getPublicId())
        .isPresent();

    assertThat(retrieved.get().getTitle()).isEqualTo("Treasure island");
  }
}
