package com.hkc.cqrs.application.book.query.getlist;

import an.awesome.pipelinr.Command;
import com.hkc.cqrs.domain.entity.Book;
import com.hkc.cqrs.persistence.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

public class GetListBookQuery implements Command<List<GetListBookItemDto>>{


    @Component
    @RequiredArgsConstructor
    public static class GetListBookQueryHandler implements Command.Handler<GetListBookQuery,List<GetListBookItemDto>>{

        private final BookRepository bookRepository;

        @Override
        public List<GetListBookItemDto> handle(GetListBookQuery getListBookQuery) {
            List<Book> books = bookRepository.findAll();

            List<GetListBookItemDto> bookDtos = books
                    .stream()
                    .map(book -> new GetListBookItemDto(book.getId(),book.getName())).toList();
            return bookDtos;
        }

    }

}
