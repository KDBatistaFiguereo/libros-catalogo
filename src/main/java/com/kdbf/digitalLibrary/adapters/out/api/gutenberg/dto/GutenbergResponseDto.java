package com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutenbergResponseDto(
    int count,
    List<GutenbergBookDto> results) {
}
