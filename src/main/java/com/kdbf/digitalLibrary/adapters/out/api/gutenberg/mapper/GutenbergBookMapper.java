package com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergAuthorDto;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergBookDto;
import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

@Mapper(componentModel = "spring")
public abstract class GutenbergBookMapper {
  @Mapping(source = "title", target = "title")
  @Mapping(source = "authors", target = "author", qualifiedByName = "getFirstAuthor")
  @Mapping(source = "languages", target = "language", qualifiedByName = "getFirstLanguage")
  @Mapping(source = "downloadCount", target = "downloads")
  public abstract Book toDomain(GutenbergBookDto dto);

  @Named("getFirstAuthor")
  protected Author getFirstAuthor(Set<GutenbergAuthorDto> authors) {
    if (authors == null || authors.isEmpty()) {
      return new Author("Anonymous", null, null);
    }
    return new Author(authors.iterator().next().name(),
        authors.iterator().next().birthYear(),
        authors.iterator().next().deathYear());
  }

  @Named("getFirstLanguage")
  protected String getFirstLanguage(List<String> languages) {
    if (languages == null || languages.isEmpty()) {
      return "Unknown";
    }
    return languages.get(0);
  }
}
