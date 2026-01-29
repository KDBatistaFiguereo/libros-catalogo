package com.kdbf.digitalLibrary.adapters.out.api.gutenberg;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.dto.GutenbergResponseDto;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper.GutenbergBookMapper;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper.GutenbergMapper;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.port.out.FindBooksPort;

@Component
public class GutenbergAdapter implements FindBooksPort {

  private final GutenbergApiClient apiClient;
  private final GutenbergMapper mapper;
  private final GutenbergBookMapper bookMapper;

  public GutenbergAdapter(GutenbergApiClient apiClient, GutenbergMapper mapper, GutenbergBookMapper bookMapper) {
    this.apiClient = apiClient;
    this.mapper = mapper;
    this.bookMapper = bookMapper;
  }

  @Override
  public List<Book> findBooksByTitle(String title) {
    title = title.trim();
    String jsonResponse = apiClient.getRawJson("search", title);
    GutenbergResponseDto responseDto = mapper.toDto(jsonResponse);

    List<Book> books = responseDto.results().stream()
        .map(x -> bookMapper.toDomain(x))
        .toList();
    return books;
  }
}
