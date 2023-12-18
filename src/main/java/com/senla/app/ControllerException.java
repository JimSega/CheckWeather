package com.senla.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.DateTimeException;
import java.util.Map;

@ControllerAdvice
public class ControllerException {
    @ExceptionHandler(DateException.class)
    public ResponseEntity<Map<String, String>> dateException() {
        return ResponseEntity.badRequest().body(Map.of("error", "Wrong  period!"));
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<Map<String, String>> handlerException() {
        return ResponseEntity.badRequest().body(Map.of("error", "Wrong format date!(YYYY-MM-dd)"));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> nullPointException() {
        return ResponseEntity.badRequest().body(Map.of("error",
                "The database does not contain records (records for the required period)"));
    }
}
