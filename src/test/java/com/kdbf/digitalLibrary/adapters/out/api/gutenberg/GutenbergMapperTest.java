package com.kdbf.digitalLibrary.adapters.out.api.gutenberg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergAuthorDto;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergResponseDto;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper.GutenbergMapper;

public class GutenbergMapperTest {

  @Test
  public void shouldConvertToDto() throws Exception {
    InputStream gutenbergResponseJson = getClass().getClassLoader()
        .getResourceAsStream("dummy-data/GutenbergResponse.json");
    String jsonString = new String(gutenbergResponseJson.readAllBytes(), StandardCharsets.UTF_8);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    GutenbergMapper mapper = new GutenbergMapper(objectMapper);

    GutenbergResponseDto responseDto = mapper.toDto(jsonString);
    GutenbergAuthorDto authorDto = new GutenbergAuthorDto("Stevenson, Robert Louis", Year.of(1850), Year.of(1894));

    assertNotNull(responseDto);
    assertEquals(2, responseDto.count());
    assertEquals(43, responseDto.results().get(0).id());
    assertEquals("The Strange Case of Dr. Jekyll and Mr. Hyde", responseDto.results().get(0).title());
    assertEquals(58843, responseDto.results().get(0).downloadCount());
    assertTrue(responseDto.results().get(0).authors().contains(authorDto));
    assertTrue(responseDto.results().get(0).languages().contains("en"));
  }

}
