package com.hkc.cqrs.application.book.rules;

import com.hkc.cqrs.domain.entity.Book;
import com.hkc.cqrs.persistence.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookBusinessRules {

    private final BookRepository bookRepository;

    public void bookShouldNotBeNull(Book book){
        if(book ==null){
            throw new IllegalArgumentException("book should not be null");
        }
    }

    public void bookShouldExistWithGivenId(UUID id) {
        bookRepository.findById(id).orElse(null);
    }

}
