package com.caterpillar.toyrobotapi.advice;

import com.caterpillar.toyrobotapi.exception.InvalidCommandException;
import com.caterpillar.toyrobotapi.exception.RobotMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ToyRobotExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { InvalidCommandException.class, RobotMissingException.class })
    protected ResponseEntity<String> handleKnownExceptions(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Throwable.class)
    protected ResponseEntity<String> handleUnKnownExceptions(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
