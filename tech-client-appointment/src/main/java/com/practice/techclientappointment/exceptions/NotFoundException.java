package com.practice.techclientappointment.exceptions;

import com.practice.techclientappointment.exceptions.model.ErrorMessage;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super(new ErrorMessage("appointmentService", "Object not found"));
    }

    public NotFoundException(ErrorMessage message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
