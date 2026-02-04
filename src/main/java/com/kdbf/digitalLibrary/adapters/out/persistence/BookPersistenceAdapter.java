package com.kdbf.digitalLibrary.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.kdbf.digitalLibrary.adapters.out.persistence.entity.AuthorJpaEntity;
import com.kdbf.digitalLibrary.adapters.out.persistence.entity.BookJpaEntity;
import com.kdbf.digitalLibrary.adapters.out.persistence.mapper.AuthorJpaMapper;
import com.kdbf.digitalLibrary.adapters.out.persistence.mapper.BookJpaMapper;
import com.kdbf.digitalLibrary.adapters.out.persistence.repository.AuthorJpaRepository;
import com.kdbf.digitalLibrary.adapters.out.persistence.repository.BookJpaRepository;
import com.kdbf.digitalLibrary.adapters.out.persistence.utility.CycleAvoidingMappingContext;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;
import com.kdbf.digitalLibrary.application.port.out.LoadBooksPort;
import com.kdbf.digitalLibrary.application.port.out.PersistBooksPort;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class BookPersistenceAdapter implements
    LoadBooksPort,
    PersistBooksPort {

  private final BookJpaRepository bookJpaRepository;
  private final BookJpaMapper bookJpaMapper;
  private final CycleAvoidingMappingContext cycleContext;
  private final AuthorJpaRepository authorJpaRepository;
  private final AuthorJpaMapper authorJpaMapper;

  @Override
  public boolean existsByPublicId(UUID publicId) {
    return bookJpaRepository.existsByPublicId(publicId);
  }

  @Override
  public Optional<Book> findByPublicId(UUID publicId) {
    return bookJpaRepository.findByPublicId(publicId)
        .map(book -> bookJpaMapper.toDomain(book, cycleContext));
  }

  private Book save(Book book) {
    BookJpaEntity entityToSave = bookJpaMapper.toJpa(book, cycleContext);
    BookJpaEntity savedEntity = bookJpaRepository.save(entityToSave);
    return bookJpaMapper.toDomain(savedEntity, cycleContext);
  }

  @Override
  @Transactional
  public Book createBook(Book book) {
    AuthorJpaEntity authorEntity = authorJpaRepository.findByName(book.getAuthor().getName())
        .orElseGet(() -> authorJpaRepository.save(authorJpaMapper.toJpa(book.getAuthor(), cycleContext)));
    BookJpaEntity bookEntity = bookJpaMapper.toJpa(book, cycleContext);
    bookEntity.setAuthor(authorEntity);

    return bookJpaMapper.toDomain(bookJpaRepository.saveAndFlush(bookEntity), cycleContext);
  }

  @Override
  public List<Book> findAll() {
    return bookJpaMapper.toDomain(bookJpaRepository.findAll(), cycleContext);
  }
}
