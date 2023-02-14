package com.practice.techclientappointment.exceptions;

import com.practice.techclientappointment.exceptions.model.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionTest {


    @Test
    public void SHOULD_THROW_RUNTIME_EXCEPTION() {

        ErrorMessage errorMessage = new ErrorMessage("testService", "test Error message");
        assertThrows(BaseException.class, () -> {
            throw new NotValidObjectException(errorMessage);
        });
    }

}