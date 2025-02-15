package com.hkc.cqrs.application.book.mapper;

import com.hkc.cqrs.application.book.command.create.CreateBookCommand;
import com.hkc.cqrs.application.book.command.create.CreatedBookResponse;
import com.hkc.cqrs.domain.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    //Target fonksiton_ismi(Source s)
    @Mapping(target = "author.id", source = "authorId") //mapstruct oto generate edebilsin diye bu özellik kullanılabilir
    Book convertCreateCommandToBook(CreateBookCommand command);

    CreatedBookResponse convertBookToCreateBookResponse(Book book);
}
