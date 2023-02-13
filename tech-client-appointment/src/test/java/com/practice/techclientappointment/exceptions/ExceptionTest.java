package com.practice.techclientappointment.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionTest {


    @Test
    public void SHOULD_THROW_RUNTIME_EXCEPTION() {


        assertThrows(BaseException.class, () -> {
            throw new NotValidObjectException("testSE R VICE - should be a valid message");
        });

    }

}