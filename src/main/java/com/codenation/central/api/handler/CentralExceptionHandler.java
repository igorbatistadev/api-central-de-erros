package com.codenation.central.api.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CentralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<ValidationObject> errors = getErrors(ex);
        ErrorValidation errorValidation = getErrorValidation(ex, status, errors);
        return new ResponseEntity<>(errorValidation, status);
    }

    private ErrorValidation getErrorValidation(MethodArgumentNotValidException ex, HttpStatus status, List<ValidationObject> errors) {
        return ErrorValidation.errorValidationBuilder()
                .title("Requisição possui campos inválidos")
                .status(status.value())
                .detail(ex.getMessage())
                .erros(errors)
                .build();
    }

    private List<ValidationObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> ValidationObject.builder()
                                .message(error.getDefaultMessage())
                                .field(error.getField())
                                .parameter(error.getRejectedValue())
                                .build())
                .collect(Collectors.toList());
    }
}
