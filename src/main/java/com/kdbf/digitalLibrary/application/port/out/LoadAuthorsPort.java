package com.kdbf.digitalLibrary.application.port.out;

import java.util.Optional;
import java.util.UUID;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;

public interface LoadAuthorsPort {
  boolean existsByPublicId(UUID publicId);

  Optional<Author> findByName(String name);

  Optional<Author> findByPublicId(UUID publicID);
}
