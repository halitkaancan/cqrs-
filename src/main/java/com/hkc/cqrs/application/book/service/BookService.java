package com.hkc.cqrs.application.book.service;

import com.hkc.cqrs.domain.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book findById(UUID id);
    List<Book> findAll();
    Book save(Book book);
    UUID delete(UUID id);

}
