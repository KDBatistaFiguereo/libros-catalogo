package com.kdbf.digitalLibrary.adapters.out.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Year;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kdbf.digitalLibrary.adapters.out.persistence.entity.AuthorJpaEntity;
import com.kdbf.digitalLibrary.adapters.out.persistence.entity.BookJpaEntity;
import com.kdbf.digitalLibrary.adapters.out.persistence.utility.CycleAvoidingMappingContext;
import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

@SpringBootTest(classes = { BookJpaMapperImpl.class, AuthorJpaMapperImpl.class })
public class BookJpaMapperTest {

  @Autowired
  private BookJpaMapper bookJpaMapper;

  @Autowired
  private AuthorJpaMapper authorJpaMapper;

  private CycleAvoidingMappingContext context;

  @BeforeEach
  void setUp() {
    context = new CycleAvoidingMappingContext();
  }

  @Test
  void testToDomain() {
    AuthorJpaEntity authorJpa = new AuthorJpaEntity(1L, "Test Author", Year.of(1980), null);

    BookJpaEntity bookJpa = new BookJpaEntity(1L, "Test Book", "English", 100, authorJpa);

    Book book = bookJpaMapper.toDomain(bookJpa, context);

    assertNotNull(book);
    assertEquals("Test Book", book.getTitle());
    assertEquals("English", book.getLanguage());
    assertEquals(100, book.getDownloads());
    assertNotNull(book.getAuthor());
    assertEquals("Test Author", book.getAuthor().getName());
    assertEquals(Year.of(1980), book.getAuthor().getBirthYear());
  }

  @Test
  void testToJpa() {
    Author author = new Author("Test Author", Year.of(1975), null);

    Book book = new Book("Test Book", author, "French", 200);

    BookJpaEntity bookJpa = bookJpaMapper.toJpa(book, context);

    assertNotNull(bookJpa);
    assertEquals("Test Book", bookJpa.getTitle());
    assertEquals("French", bookJpa.getLanguage());
    assertEquals(200, bookJpa.getDownloads());
    assertNotNull(bookJpa.getAuthor());
    assertEquals("Test Author", bookJpa.getAuthor().getName());
    assertEquals(Year.of(1975), bookJpa.getAuthor().getBirthYear());
  }

  @Test
  void testToDomainList() {
    AuthorJpaEntity authorJpa = new AuthorJpaEntity(1L, "Test Author", Year.of(1980), null);

    BookJpaEntity bookJpa1 = new BookJpaEntity(1L, "Book 1", "English", 100, authorJpa);
    BookJpaEntity bookJpa2 = new BookJpaEntity(2L, "Book 2", "Spanish", 200, authorJpa);

    List<BookJpaEntity> bookJpaList = List.of(bookJpa1, bookJpa2);
    List<Book> books = bookJpaMapper.toDomain(bookJpaList, context);

    assertNotNull(books);
    assertEquals(2, books.size());
    assertEquals("Book 1", books.get(0).getTitle());
    assertEquals("Book 2", books.get(1).getTitle());
  }

  @Test
  void testToJpaList() {
    Author author = new Author("Test Author", Year.of(1980), null);

    Book book1 = new Book("Book 1", author, "English", 100);
    Book book2 = new Book("Book 2", author, "Spanish", 200);

    List<Book> books = List.of(book1, book2);
    List<BookJpaEntity> bookJpaList = bookJpaMapper.toJpa(books, context);

    assertNotNull(bookJpaList);
    assertEquals(2, bookJpaList.size());
    assertEquals("Book 1", bookJpaList.get(0).getTitle());
    assertEquals("Book 2", bookJpaList.get(1).getTitle());
  }
}
