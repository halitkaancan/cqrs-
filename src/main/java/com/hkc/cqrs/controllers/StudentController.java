package com.hkc.cqrs.controllers;

import an.awesome.pipelinr.Pipeline;
import com.hkc.cqrs.application.student.command.create.CreateStudentCommand;
import com.hkc.cqrs.application.student.command.create.CreatedStudentResponse;
import com.hkc.cqrs.core.web.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController extends BaseController {


    public StudentController(Pipeline pipeline) {
        super(pipeline);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreatedStudentResponse create(@RequestBody CreateStudentCommand command) {
        return command.execute(pipeline);
    }
}
