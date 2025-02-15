package com.hkc.cqrs.application.student.command.create;

import an.awesome.pipelinr.Command;
import com.hkc.cqrs.application.cart.service.CartService;
import com.hkc.cqrs.application.student.mapper.StudentMapper;
import com.hkc.cqrs.domain.entity.Student;
import com.hkc.cqrs.persistence.student.StudentRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class CreateStudentCommand implements Command<CreatedStudentResponse> {

    //TODO Validations needed
    private String email;
    private String password;

    @Component
    @RequiredArgsConstructor
    public static class CreateStudentCommandHandler
    implements Command.Handler<CreateStudentCommand, CreatedStudentResponse>{

        private final StudentRepository studentRepository;
        private final CartService cartService;


        @Override
        public CreatedStudentResponse handle(CreateStudentCommand createStudentCommand) {

            StudentMapper studentMapper = StudentMapper.INSTANCE;
            Student student = studentMapper.creatStudentFromCreateCommand(createStudentCommand);
            studentRepository.save(student);

            cartService.createCartForStudent(student);

            return studentMapper.creatCreatedResponseStudentFromCreateCommand(student);
        }
    }

}
