package ru.linux.backend.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.linux.backend.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(basePackages = "ru.linux.backend.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorDto> handleException(NotFoundException e) {
        return getErrorDtoResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }



    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        var errors = new ArrayList<String>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(objectError ->
                errors.add(objectError.getObjectName() + ": " + objectError.getDefaultMessage()));

        var apiError = new ErrorDto("Argument(s) did not validate", errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<ErrorDto> getErrorDtoResponseEntity(HttpStatus httpStatus, String message) {
        var response = new ErrorDto(message, List.of(String.valueOf(httpStatus.value())));
        return new ResponseEntity<>(response, httpStatus);
    }
}
