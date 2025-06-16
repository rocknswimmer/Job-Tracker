package job_tracker.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException exception) {
        return ErrorResponse.build("Error in the database, please check that referenced records exist. Request Failed.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) throws Exception {
        if (exception instanceof HttpMessageNotReadableException || exception instanceof HttpMediaTypeNotSupportedException) {
            throw exception;
        }

        return ErrorResponse.build("Something when wrong, your request failed.");
    }
}
