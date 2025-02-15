package com.hkc.cqrs.application.student.mapper;

import com.hkc.cqrs.application.student.command.create.CreateStudentCommand;
import com.hkc.cqrs.application.student.command.create.CreatedStudentResponse;
import com.hkc.cqrs.domain.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Autowired
    protected  PasswordEncoder passwordEncoder;

    @Mapping(target = "studentNo", expression = "java(mapStudentNo())")
    @Mapping(target = "password",expression = "java(mapStudentPassword(createStudentCommand.getPassword()))")
    @Mapping(target = "email", expression = "java(createStudentCommand.getEmail())")
    public abstract Student creatStudentFromCreateCommand(CreateStudentCommand createStudentCommand);

    @Mapping(target = "email", expression = "java(student.getEmail())")
    @Mapping(target = "studentNo", expression = "java(student.getStudentNo())")
    public abstract CreatedStudentResponse creatCreatedResponseStudentFromCreateCommand(Student student);

     protected String mapStudentNo(){
        int randomNumber=(int)(Math.random()*9+1);
        return String.valueOf(randomNumber);
    }
    protected String mapStudentPassword(String password){
         return passwordEncoder.encode(password);
    }

}
