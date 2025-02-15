package com.hkc.cqrs.application.book.service;

import com.hkc.cqrs.domain.entity.Book;
import com.hkc.cqrs.persistence.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;

    @Override
    public Book findById(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public UUID delete(UUID id) {
        bookRepository.deleteById(id);
        return id;
    }
}
