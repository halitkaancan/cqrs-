package com.hkc.cqrs.persistence.student;

import com.hkc.cqrs.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}
