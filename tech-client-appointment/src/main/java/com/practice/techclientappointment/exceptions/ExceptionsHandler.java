package com.practice.techclientappointment.exceptions;

import com.practice.techclientappointment.exceptions.model.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDetails> handleApiExceptions(BaseException ex, WebRequest request) {
        ErrorDetails details = ErrorDetails.builder()
                .message(ex.getMessage())
                .uri(request.getDescription(false))
                .build();
        return new ResponseEntity<>(details, ex.getStatusCode());
    }
}
