package com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto;

import java.time.Year;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GutenbergAuthorDto(
    String name,
    @JsonProperty("birth_year") Year birthYear,
    @JsonProperty("death_year") Year deathYear) {
}
