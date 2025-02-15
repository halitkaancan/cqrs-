package com.hkc.cqrs.application.student.service;

import com.hkc.cqrs.domain.entity.Student;
import com.hkc.cqrs.persistence.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;
    @Override
    public Student findStudentById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }
}
