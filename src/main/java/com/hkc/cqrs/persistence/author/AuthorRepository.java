package com.hkc.cqrs.persistence.author;

import com.hkc.cqrs.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
