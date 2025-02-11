package com.hkc.cqrs.persistence.book;

import com.hkc.cqrs.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> { }

