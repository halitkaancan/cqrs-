package com.hkc.cqrs.application.author.service;

import com.hkc.cqrs.domain.entity.Author;
import com.hkc.cqrs.persistence.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;
    @Override
    public Author findById(UUID id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();

    }

    @Override
    public Author save(Author author) {
       return authorRepository.save(author);
    }

    @Override
    public UUID deleted(UUID id) {
        authorRepository.deleteById(id);
        return id;
    }
}
