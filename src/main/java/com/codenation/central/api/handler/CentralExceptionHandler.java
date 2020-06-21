package com.codenation.central.api.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CentralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .title("Recurso não encontrado")
                .status(HttpStatus.NOT_FOUND.value())
                .detail(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<ValidationObject> errors = getErrors(ex);
        ErrorValidation errorValidation = getErrorValidation(ex, status, errors);
        return new ResponseEntity<>(errorValidation, headers, status);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .title("Violação de restrições")
                .status(HttpStatus.CONFLICT.value())
                .detail(ex.getRootCause().getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .title("Erro interno")
                .status(status.value())
                .detail(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, headers, status);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .title("JSON mal formatado")
                .status(status.value())
                .detail(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, headers, status);
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
