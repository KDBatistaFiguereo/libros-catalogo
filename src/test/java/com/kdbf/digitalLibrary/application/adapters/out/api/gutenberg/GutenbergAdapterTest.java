package com.kdbf.digitalLibrary.application.adapters.out.api.gutenberg;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.GutenbergAdapter;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.GutenbergApiClient;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper.GutenbergBookMapper;
import com.kdbf.digitalLibrary.adapters.out.api.gutenberg.mapper.GutenbergMapper;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class GutenbergAdapterTest {
  public static final GutenbergBookMapper bookMapper = Mappers.getMapper(GutenbergBookMapper.class);
  private static MockWebServer mockWebServer;
  private GutenbergAdapter gutenbergAdapter;
  private GutenbergApiClient apiClient;
  private ObjectMapper objectMapper;
  private GutenbergMapper gutenbergMapper;

  @BeforeAll
  static void setUp() {
    try {
      mockWebServer = new MockWebServer();
      mockWebServer.start();

    } catch (IOException e) {
      throw new UncheckedIOException("Could not start the mockserver", e);
    }

  }

  @AfterAll
  static void close() throws Exception {
    mockWebServer.shutdown();
  }

  @BeforeEach
  void initialize() {
    String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
    apiClient = new GutenbergApiClient(baseUrl);
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    gutenbergMapper = new GutenbergMapper(objectMapper);
    gutenbergAdapter = new GutenbergAdapter(apiClient, gutenbergMapper, bookMapper);
  }

  @Test
  void shouldReturnListOfBooks() throws Exception {
    InputStream gutenbergResponseJson = getClass().getClassLoader()
        .getResourceAsStream("dummy-data/GutenbergResponse.json");
    String jsonString = new String(gutenbergResponseJson.readAllBytes(), StandardCharsets.UTF_8);

    mockWebServer.enqueue(new MockResponse()
        .setBody(jsonString)
        .setResponseCode(200)
        .addHeader("Content-Type", "application/json"));

    List<Book> myBooks = gutenbergAdapter.findBooksByTitle("jekyll");

    assertEquals(2, myBooks.size());
    assertEquals("The Strange Case of Dr. Jekyll and Mr. Hyde", myBooks.get(0).getTitle());
    assertEquals("The Strange Case of Dr. Jekyll and Mr. Hyde", myBooks.get(1).getTitle());
    assertEquals("Stevenson, Robert Louis", myBooks.get(0).getAuthor().getName());
    assertEquals(58843, myBooks.get(0).getDownloads());
    assertEquals("en", myBooks.get(0).getLanguage());

    RecordedRequest recordedRequest = mockWebServer.takeRequest();
    assertEquals("GET", recordedRequest.getMethod());
    assertTrue(recordedRequest.getPath().contains("search=jekyll"));

  }
}
