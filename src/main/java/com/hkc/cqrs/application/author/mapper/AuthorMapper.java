package com.hkc.cqrs.application.author.mapper;

import com.hkc.cqrs.application.author.command.create.CreateAuthorCommand;
import com.hkc.cqrs.application.author.command.create.CreatedAuthorResponse;
import com.hkc.cqrs.domain.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper
{
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author createAuthorFromCreateCommand(CreateAuthorCommand createAuthorCommand);
    CreatedAuthorResponse createAuthorResponseFromAuthor(Author author);
}
