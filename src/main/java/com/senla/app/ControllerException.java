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
        logger.error(ErrorMessage.WRONG_PERIOD.toString());
        return ResponseEntity.badRequest().body(Map.of("error", ErrorMessage.WRONG_PERIOD.toString()));
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<Map<String, String>> handlerException() {
        logger.error(ErrorMessage.WRONG_FORMAT.toString());
        return ResponseEntity.badRequest().body(Map.of("error", ErrorMessage.WRONG_FORMAT.toString()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> nullPointException() {
        logger.warn(ErrorMessage.NOT_CONTAIN_RECORDS.toString());
        return ResponseEntity.badRequest().body(Map.of("WARN", ErrorMessage.NOT_CONTAIN_RECORDS.toString()));
    }
}
