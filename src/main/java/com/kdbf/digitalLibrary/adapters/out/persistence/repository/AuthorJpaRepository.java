package com.kdbf.digitalLibrary.adapters.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdbf.digitalLibrary.adapters.out.persistence.entity.AuthorJpaEntity;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorJpaEntity, Long> {
  public Optional<AuthorJpaEntity> findByName(String name);

  public boolean existsByPublicId(UUID publicId);

  public Optional<AuthorJpaEntity> findByPublicId(UUID id);

}
