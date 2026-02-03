package com.kdbf.digitalLibrary.adapters.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.kdbf.digitalLibrary.adapters.out.persistence.entity.AuthorJpaEntity;
import com.kdbf.digitalLibrary.adapters.out.persistence.mapper.AuthorJpaMapper;
import com.kdbf.digitalLibrary.adapters.out.persistence.repository.AuthorJpaRepository;
import com.kdbf.digitalLibrary.adapters.out.persistence.utility.CycleAvoidingMappingContext;
import com.kdbf.digitalLibrary.application.domain.model.entity.Author;
import com.kdbf.digitalLibrary.application.port.out.LoadAuthorsPort;
import com.kdbf.digitalLibrary.application.port.out.PersistAuthorsPort;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AuthorPersistenceAdapter implements
    LoadAuthorsPort,
    PersistAuthorsPort {

  private final AuthorJpaRepository authorJpaRepository;
  private final AuthorJpaMapper authorJpaMapper;
  private final CycleAvoidingMappingContext cycleContext;

  @Override
  public boolean existsByPublicId(UUID publicId) {
    return authorJpaRepository.existsByPublicId(publicId);
  }

  @Override
  public Optional<Author> findByName(String name) {
    return authorJpaRepository.findByName(name)
        .map(author -> authorJpaMapper.toDomain(author, cycleContext));
  }

  @Override
  public Optional<Author> findByPublicId(UUID publicId) {
    return authorJpaRepository.findByPublicId(publicId)
        .map(author -> authorJpaMapper.toDomain(author, cycleContext));
  }

  @Override
  public Author save(Author author) {
    AuthorJpaEntity entityToSave = authorJpaMapper.toJpa(author, cycleContext);
    AuthorJpaEntity savedEntity = authorJpaRepository.save(entityToSave);
    return authorJpaMapper.toDomain(savedEntity, cycleContext);
  }

}
