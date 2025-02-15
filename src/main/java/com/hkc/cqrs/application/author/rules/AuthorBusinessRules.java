package com.hkc.cqrs.application.author.rules;

import com.hkc.cqrs.domain.entity.Author;
import com.hkc.cqrs.persistence.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthorBusinessRules {

    private final AuthorRepository authorRepository;
    public void authorCanNotBeNull(Author author) {
        if (author == null) {
            throw new RuntimeException("Author can not be null");
        }

    }
    public void authorWithIdCanNotBeNull(UUID authorId ) {

        authorRepository.findById(authorId).orElseThrow(()-> new RuntimeException("Author with id " + authorId + " not found"));

    }
}
