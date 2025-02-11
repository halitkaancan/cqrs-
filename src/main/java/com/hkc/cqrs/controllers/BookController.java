package com.hkc.cqrs.controllers;

import an.awesome.pipelinr.Pipeline;
import com.hkc.cqrs.application.book.command.create.CreateBookCommand;
import com.hkc.cqrs.application.book.command.create.CreatedBookResponse;
import com.hkc.cqrs.application.book.command.update.UpdateBookCommand;
import com.hkc.cqrs.application.book.command.update.UpdatedBookResponse;
import com.hkc.cqrs.core.web.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController extends BaseController {


    public BookController(Pipeline pipeline) {
        super(pipeline);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBookResponse create(@RequestBody CreateBookCommand createBookCommand){
        return createBookCommand.execute(pipeline);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedBookResponse update(@RequestBody UpdateBookCommand updateBookCommand){
        return updateBookCommand.execute(pipeline);
    }
}
