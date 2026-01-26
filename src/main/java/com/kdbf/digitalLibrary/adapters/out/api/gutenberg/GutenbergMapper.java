package com.kdbf.digitalLibrary.adapters.out.api.gutenberg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergResponseDto;

public class GutenbergMapper {
  private final ObjectMapper objectMapper;

  public GutenbergMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public GutenbergResponseDto toDto(String jsonResponse) throws Exception {
    GutenbergResponseDto response = objectMapper.readValue(jsonResponse, GutenbergResponseDto.class);
    return response;
  }

}
