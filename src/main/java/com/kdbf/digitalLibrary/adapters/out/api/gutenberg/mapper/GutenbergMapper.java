package com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper;

import java.io.IOException;
import java.io.UncheckedIOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergResponseDto;

public class GutenbergMapper {
  private final ObjectMapper objectMapper;

  public GutenbergMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public GutenbergResponseDto toDto(String jsonResponse) {
    try {
      GutenbergResponseDto response = objectMapper.readValue(jsonResponse, GutenbergResponseDto.class);
      return response;
    } catch (IOException e) {
      throw new UncheckedIOException("Could not process the JSON", e);
    }
  }
}
