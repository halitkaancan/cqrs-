package com.hkc.cqrs.application.student.service;

import com.hkc.cqrs.domain.entity.Student;

import java.util.UUID;

public interface StudentService {
    Student findStudentById(UUID id);
}
