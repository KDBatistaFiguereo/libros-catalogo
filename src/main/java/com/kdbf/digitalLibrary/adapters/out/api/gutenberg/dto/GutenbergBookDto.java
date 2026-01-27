package com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutenbergBookDto(
    int id,
    String title,
    // Necessary to use LinkedHashSet when retrieving the first author
    @JsonDeserialize(as = LinkedHashSet.class) Set<GutenbergAuthorDto> authors,
    List<String> summaries,
    List<String> editors,
    List<String> translators,
    List<String> subjects,
    List<String> bookshelves,
    List<String> languages,
    boolean copyright,
    @JsonProperty("media_type") String mediaType,
    @JsonProperty("download_count") int downloadCount,
    @JsonIgnore Object formats) {
}
