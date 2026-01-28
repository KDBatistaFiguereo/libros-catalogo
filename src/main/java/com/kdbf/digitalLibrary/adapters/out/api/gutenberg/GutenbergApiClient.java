package com.kdbf.digitalLibrary.adapters.out.api.gutenberg;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutenbergApiClient {
  private HttpClient client;
  private String baseUrl;

  public GutenbergApiClient(String baseUrl) {
    this.client = HttpClient.newHttpClient();
    this.baseUrl = baseUrl;
  }

  public String getRawJson(String endpoint) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(baseUrl + endpoint))
          .GET()
          .build();
      return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Thread was interrupted while processing", e);
    } catch (IOException e) {
      throw new UncheckedIOException("Data transfer failed", e);
    }
  }
}
