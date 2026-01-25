package com.kdbf.digitalLibrary.application.adapters.out.api.gutenberg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public class GutenbergMapperTest {

  @Test
  public void shouldConvertToDto() {
    File gutenbergResponseJson = new File("/resources/dummy-data/GutenbergResponse.json");
    GutenbergMapper mapper() = new GutenbergMapper();
    GutenbergResponseDto responseDto = mapper.toDto(gutenbergResponseJson);
    GutenbergAuthorDto authorDto = 
      new GutenbergAuthorDto("Stevenson, Robert Louis", 1850, 1894);

    assertNotNull(responseDto);
    assertEquals(2, responseDto.count());
    assertEquals(43, responseDto.results().get(0).id());
    assertEquals("The Strange Case of Dr. Jekyll and Mr. Hyde", responseDto.results().get(0).title());
    assertEquals(58843, responseDto.results().get(0).downloadCount());
    assertTrue(responseDto.results().get(0).authors().contains(authorDto));
    assertTrue(responseDto.results().get(0).languages().contains("en"));
  }

}
