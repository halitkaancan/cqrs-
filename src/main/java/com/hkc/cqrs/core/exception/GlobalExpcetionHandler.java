package com.hkc.cqrs.core.exception;

import com.hkc.cqrs.core.exception.type.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExpcetionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<List<String>> validationException(ValidationException e) {
        return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);


//                "Validation exception "+e.getMessage(),
//                HttpStatus.BAD_REQUEST);
    }
}
