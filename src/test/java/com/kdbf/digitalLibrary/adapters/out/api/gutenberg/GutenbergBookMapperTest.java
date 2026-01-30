package com.kdbf.digitalLibrary.adapters.out.api.gutenberg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Year;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergAuthorDto;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergBookDto;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper.GutenbergBookMapper;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public class GutenbergBookMapperTest {
  public static final GutenbergBookMapper mapper = Mappers.getMapper(GutenbergBookMapper.class);

  @Test
  public void shouldConvertToDomain() {
    GutenbergBookDto bookDto = getBookDto();
    Book myBook = mapper.toDomain(bookDto);
    String expected = bookDto.authors().iterator().next().name();

    assertNotNull(myBook);
    assertEquals(bookDto.title(), myBook.getTitle());
    assertEquals(expected, myBook.getAuthor().getName());
    assertEquals(bookDto.languages().getFirst(), myBook.getLanguage());
    assertEquals(bookDto.downloadCount(), myBook.getDownloads());
  }

  private GutenbergBookDto getBookDto() {
    GutenbergAuthorDto authorDto = new GutenbergAuthorDto(
        "Stevenson, Robert Louis",
        Year.of(1850),
        Year.of(1894));

    GutenbergBookDto bookDto = new GutenbergBookDto(
        43,
        "The Strange Case of Dr. Jekyll and Mr. Hyde",
        Set.of(authorDto),
        List.of("", ""),
        List.of(""),
        List.of(""),
        List.of("en"),
        false,
        "text",
        58843);
    return bookDto;
  }
}
