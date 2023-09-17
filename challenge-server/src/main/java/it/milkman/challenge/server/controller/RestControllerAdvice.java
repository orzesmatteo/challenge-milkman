package it.milkman.challenge.server.controller;

import it.milkman.challenge.server.exception.OrderStatusBlockOperationException;
import it.milkman.challenge.server.exception.ResourceMismatchException;
import it.milkman.challenge.server.exception.ResourceNotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceMismatchException.class})
    public ResponseEntity<ErrorMessage> resourceMismatchException(ResourceMismatchException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {OrderStatusBlockOperationException.class})
    public ResponseEntity<ErrorMessage> orderStatusBlockOperationException(OrderStatusBlockOperationException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

}
