package com.capgemini.hospital.management.exception;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, Object> handleNotFound(ResourceNotFoundException ex) {
        return Map.of("status", 404, "error", "Not Found", "message", ex.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public Map<String, Object> handleDuplicate(DuplicateResourceException ex) {
        return Map.of("status", 409, "error", "Conflict", "message", ex.getMessage());
    }
}