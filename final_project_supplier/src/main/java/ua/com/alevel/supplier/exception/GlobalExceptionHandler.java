package ua.com.alevel.supplier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { AuthException.class })
    public ResponseEntity<String> handleConflict(AuthException authException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authException.getMessage());
    }
}
