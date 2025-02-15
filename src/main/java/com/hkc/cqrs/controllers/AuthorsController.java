package com.hkc.cqrs.controllers;

import an.awesome.pipelinr.Pipeline;
import com.hkc.cqrs.application.author.command.create.CreateAuthorCommand;
import com.hkc.cqrs.application.author.command.create.CreatedAuthorResponse;
import com.hkc.cqrs.core.web.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorsController extends BaseController {

    public AuthorsController(Pipeline pipeline) {super(pipeline);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAuthorResponse create(@RequestBody CreateAuthorCommand createAuthorCommand){
        return createAuthorCommand.execute(pipeline);
    }




}
