package com.restaurant.api.exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<@NonNull Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", message);
        errorDetails.put("status", status.value());
        return new ResponseEntity<>(errorDetails, status);
    }

    // Business logic
    // 400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<@NonNull Object> handleBadRequestException(BadRequestException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // 404
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<@NonNull Object> handleItemNotFoundException(ItemNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 409
    @ExceptionHandler(ResourceInUseException.class)
    public ResponseEntity<@NonNull Object> handleResourceInUseException(ResourceInUseException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // From Spring
    //400, DTO field validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NonNull Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("errors", errors);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // 400, when someone type text instead of ID in URL
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<@NonNull Object> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String msg = "Parameter " + ex.getName() + " should be type " +
                (ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "correct type");
        return buildResponse(HttpStatus.BAD_REQUEST, msg);
    }

    // 400, when someone sends malformed JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<@NonNull Object> handleMalformedJson(HttpMessageNotReadableException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Malformed JSON request body or incorrect data format");
    }

    // 405, e.g. when someone confuses GET with POST
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<@NonNull Object> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
    }

    // 415, e.g. when someone sends Text instead of JSON
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<@NonNull Object> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        return buildResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
    }

    // 500, when the database crashes or a sudden error occurs
    @ExceptionHandler(Exception.class)
    public ResponseEntity<@NonNull Object> handleGlobalException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred on the server");
    }
}
