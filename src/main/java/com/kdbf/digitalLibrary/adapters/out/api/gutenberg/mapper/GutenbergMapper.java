package com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergBookDto;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergResponseDto;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public class GutenbergMapper {
  private final ObjectMapper objectMapper;

  public GutenbergMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public GutenbergResponseDto toDto(String jsonResponse) throws Exception {
    GutenbergResponseDto response = objectMapper.readValue(jsonResponse, GutenbergResponseDto.class);
    return response;
  }

  public Book toDomain(GutenbergBookDto bookDto) {
    return null;
  }

}
