package com.kdbf.digitalLibrary.application.port.out;

import java.util.Optional;
import java.util.UUID;

import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

public interface LoadBooksPort {
  Optional<Book> findByPublicId(UUID publicId);

  boolean existsByPublicId(UUID publicId);
}
