package com.kdbf.digitalLibrary.application.adapters.out.api.gutenberg;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.print.Book;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.MockRestServiceServer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class GutenbergAdapterTest {
  private static MockWebServer mockWebServer;
  private GutenbergAdapter gutenbergAdapter;

  @BeforeAll
  void setUp() {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
  }

  @AfterAll
  void close() {
    mockWebServer.shutdown();
  }

  @BeforeEach
  void initialize() {
    String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
    GutenbergApiClient apiClient = new GutenbergApiClient(baseUrl);
    gutenbergAdapter = new GutenbergAdapter(apiClient);
  }

  @Test
  void shouldReturnListOfBooks() {
    InputStream gutenbergResponseJson = getClass().getClassLoader()
        .getResourceAsStream("dummy-data/GutenbergResponseMany.json");
    String jsonString = new String(gutenbergResponseJson.readAllBytes(), StandardCharsets.UTF_8);

    mockWebServer.enqueue(new MockResponse()
        .setBody(jsonString)
        .setResponseCode(200)
        .addHeader("Content-Type", "application/json"));

    List<Book> myBooks = adapter.findByTitle("jekyll");

    assertEquals(3, myBooks.size());
    assertEquals("The Strange Case of Dr Jekyll & Mr Hyde", myBooks.get(0));
    assertEquals("The Strange Case of Dr Jekyll & Mr Hyde", myBooks.get(1));
    assertEquals("The Strange Case of Dr Jekyll & Mr Hyde", myBooks.get(2));

    RecordedRequest recordedRequest = mockWebServer.takeRequest();
    assertEquals("GET", recordedRequest.getMethod());
    assertTrue(recordedRequest.getPath().contains("search=jekyll"));

  }
}
