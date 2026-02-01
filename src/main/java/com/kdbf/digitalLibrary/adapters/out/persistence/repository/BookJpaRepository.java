package com.kdbf.digitalLibrary.adapters.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdbf.digitalLibrary.adapters.out.persistence.BookJpaEntity;

public interface BookJpaRepository extends JpaRepository<BookJpaEntity, Long> {

  Optional<BookJpaEntity> findByPublicId(UUID publicId);

  Boolean existsByPublicId(UUID publicId);

}
