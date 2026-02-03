package com.kdbf.digitalLibrary.adapters.out.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Year;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kdbf.digitalLibrary.adapters.out.persistence.entity.AuthorJpaEntity;
import com.kdbf.digitalLibrary.adapters.out.persistence.utility.CycleAvoidingMappingContext;
import com.kdbf.digitalLibrary.application.domain.model.entity.Author;

@SpringBootTest(classes = { AuthorJpaMapperImpl.class })
public class AuthorJpaMapperTest {

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

    Author author = authorJpaMapper.toDomain(authorJpa, context);

    assertNotNull(author);
    assertEquals("Test Author", author.getName());
    assertEquals(Year.of(1980), author.getBirthYear());
    assertNull(author.getDeathYear());
  }

  @Test
  void testToDomainWithDeathYear() {
    AuthorJpaEntity authorJpa = new AuthorJpaEntity(1L, "Test Author", Year.of(1960), Year.of(2020));

    Author author = authorJpaMapper.toDomain(authorJpa, context);

    assertNotNull(author);
    assertEquals("Test Author", author.getName());
    assertEquals(Year.of(1960), author.getBirthYear());
    assertEquals(Year.of(2020), author.getDeathYear());
  }

  @Test
  void testToJpa() {
    Author author = new Author("Test Author", Year.of(1985), null);

    AuthorJpaEntity authorJpa = authorJpaMapper.toJpa(author, context);

    assertNotNull(authorJpa);
    assertEquals("Test Author", authorJpa.getName());
    assertEquals(Year.of(1985), authorJpa.getBirthYear());
    assertNull(authorJpa.getDeathYear());
  }

  @Test
  void testToJpaWithDeathYear() {
    Author author = new Author("Test Author", Year.of(1950), Year.of(2010));

    AuthorJpaEntity authorJpa = authorJpaMapper.toJpa(author, context);

    assertNotNull(authorJpa);
    assertEquals("Test Author", authorJpa.getName());
    assertEquals(Year.of(1950), authorJpa.getBirthYear());
    assertEquals(Year.of(2010), authorJpa.getDeathYear());
  }

  @Test
  void testToDomainList() {
    AuthorJpaEntity authorJpa1 = new AuthorJpaEntity(1L, "Author 1", Year.of(1980), null);
    AuthorJpaEntity authorJpa2 = new AuthorJpaEntity(2L, "Author 2", Year.of(1970), Year.of(2020));

    List<AuthorJpaEntity> authorJpaList = List.of(authorJpa1, authorJpa2);
    List<Author> authors = authorJpaMapper.toDomain(authorJpaList, context);

    assertNotNull(authors);
    assertEquals(2, authors.size());
    assertEquals("Author 1", authors.get(0).getName());
    assertEquals("Author 2", authors.get(1).getName());
    assertEquals(Year.of(1980), authors.get(0).getBirthYear());
    assertEquals(Year.of(1970), authors.get(1).getBirthYear());
    assertNull(authors.get(0).getDeathYear());
    assertEquals(Year.of(2020), authors.get(1).getDeathYear());
  }

  @Test
  void testToJpaList() {
    Author author1 = new Author("Author 1", Year.of(1960), null);
    Author author2 = new Author("Author 2", Year.of(1975), Year.of(2015));

    List<Author> authors = List.of(author1, author2);
    List<AuthorJpaEntity> authorJpaList = authorJpaMapper.toJpa(authors, context);

    assertNotNull(authorJpaList);
    assertEquals(2, authorJpaList.size());
    assertEquals("Author 1", authorJpaList.get(0).getName());
    assertEquals("Author 2", authorJpaList.get(1).getName());
    assertEquals(Year.of(1960), authorJpaList.get(0).getBirthYear());
    assertEquals(Year.of(1975), authorJpaList.get(1).getBirthYear());
    assertNull(authorJpaList.get(0).getDeathYear());
    assertEquals(Year.of(2015), authorJpaList.get(1).getDeathYear());
  }
}
