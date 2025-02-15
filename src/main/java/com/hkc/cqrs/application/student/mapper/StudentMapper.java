package com.hkc.cqrs.application.student.mapper;

import com.hkc.cqrs.application.student.command.create.CreateStudentCommand;
import com.hkc.cqrs.application.student.command.create.CreatedStudentResponse;
import com.hkc.cqrs.domain.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "studentNo", expression = "java(mapStudentNo())")
    Student creatStudentFromCreateCommand(CreateStudentCommand createStudentCommand);

    CreatedStudentResponse creatCreatedResponseStudentFromCreateCommand(Student student);

    default String mapStudentNo(){
        int randomNumber=(int)(Math.random()*9+1);
        return String.valueOf(randomNumber);
    }

}
