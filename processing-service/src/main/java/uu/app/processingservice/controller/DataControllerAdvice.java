package uu.app.processingservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@Slf4j
@RestControllerAdvice(assignableTypes = DataController.class)
public class DataControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<?> runtimeExceptionHandler(HttpServerErrorException.InternalServerError ex) {
        log.error("Internal server error during call to another service", ex);
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(Exception ex) {
        log.error("Runtime exception caught", ex);
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
