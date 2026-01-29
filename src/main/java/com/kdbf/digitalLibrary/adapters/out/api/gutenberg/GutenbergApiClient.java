package com.kdbf.digitalLibrary.adapters.out.api.gutenberg;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GutenbergApiClient {
  private final HttpClient client;

  private final String baseUrl;

  public GutenbergApiClient(@Value("${api.gutenberg.base-url}") final String baseUrl) {
    this.client = HttpClient.newHttpClient();
    this.baseUrl = baseUrl;
  }

  public String getRawJson(String paramName, String valueParam) {
    try {
      URI uri = UriComponentsBuilder.fromUriString(baseUrl)
          .queryParam(paramName, valueParam)
          .build()
          .encode()
          .toUri();

      HttpRequest request = HttpRequest.newBuilder()
          .uri(uri)
          .GET()
          .build();
      return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Thread was interrupted while processing", e);
    } catch (final IOException e) {
      throw new UncheckedIOException("Data transfer failed", e);
    }
  }
}
