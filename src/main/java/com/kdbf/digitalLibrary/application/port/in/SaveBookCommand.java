package com.kdbf.digitalLibrary.application.port.in;

import java.time.Year;

public record SaveBookCommand(
    String title,
    String authorName,
    Year birthYear,
    Year deathYear,
    String language,
    int download) {
}
