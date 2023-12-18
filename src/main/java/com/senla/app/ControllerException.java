package com.senla.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.DateTimeException;
import java.util.Map;

@ControllerAdvice
public class ControllerException {
    private final Logger logger = LoggerFactory.getLogger(AppApplication.class);
    @ExceptionHandler(DateException.class)
    public ResponseEntity<Map<String, String>> dateException() {
        logger.error("Wrong  period!");
        return ResponseEntity.badRequest().body(Map.of("error", "Wrong  period!"));
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<Map<String, String>> handlerException() {
        logger.error("Wrong format date!(YYYY-MM-dd)");
        return ResponseEntity.badRequest().body(Map.of("error", "Wrong format date!(YYYY-MM-dd)"));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> nullPointException() {
        logger.warn("The database does not contain records (records for the required period)");
        return ResponseEntity.badRequest().body(Map.of("WARN",
                "The database does not contain records (records for the required period)"));
    }
}
