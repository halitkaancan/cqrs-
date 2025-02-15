package com.hkc.cqrs.application.author.service;

import com.hkc.cqrs.domain.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    Author findById(UUID id);
    List<Author> findAll();
    Author save(Author author);
    UUID deleted(UUID id);
}
