package com.kdbf.digitalLibrary.adapters.out.persistence.mapper;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.kdbf.digitalLibrary.adapters.out.persistence.entity.AuthorJpaEntity;
import com.kdbf.digitalLibrary.adapters.out.persistence.utility.CycleAvoidingMappingContext;
import com.kdbf.digitalLibrary.application.domain.model.entity.Author;

@Mapper(componentModel = "spring")
public interface AuthorJpaMapper {

  Author toDomain(AuthorJpaEntity jpaEntity, @Context CycleAvoidingMappingContext context);

  AuthorJpaEntity toJpa(Author author, @Context CycleAvoidingMappingContext context);

  List<Author> toDomain(List<AuthorJpaEntity> jpaEntities, @Context CycleAvoidingMappingContext context);

  List<AuthorJpaEntity> toJpa(List<Author> authors, @Context CycleAvoidingMappingContext context);

}
