package com.kdbf.digitalLibrary.adapters.out.persistence.mapper;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.kdbf.digitalLibrary.adapters.out.persistence.entity.BookJpaEntity;
import com.kdbf.digitalLibrary.adapters.out.persistence.utility.CycleAvoidingMappingContext;
import com.kdbf.digitalLibrary.application.domain.model.entity.Book;

@Mapper(componentModel = "spring", uses = { AuthorJpaMapper.class })
public interface BookJpaMapper {

  Book toDomain(BookJpaEntity jpaEntity, @Context CycleAvoidingMappingContext context);

  BookJpaEntity toJpa(Book book, @Context CycleAvoidingMappingContext context);

  List<Book> toDomain(List<BookJpaEntity> jpaEntities, @Context CycleAvoidingMappingContext context);

  List<BookJpaEntity> toJpa(List<Book> books, @Context CycleAvoidingMappingContext context);

}
