package com.kdbf.digitalLibrary.application.port.out;

import com.kdbf.digitalLibrary.application.domain.model.entity.Author;

public interface PersistAuthorsPort {
  Author save(Author author);
}
