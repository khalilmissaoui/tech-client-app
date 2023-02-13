package com.practice.techclientappointment.validations.implementaions;

import com.practice.techclientappointment.validations.annotations.ExceptionMessageValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExceptionMessageValidator implements
        ConstraintValidator<ExceptionMessageValidation, String> {

    @Override
    public void initialize(ExceptionMessageValidation exceptionMessage) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("^[A-Za-z-0-9]*SERVICE-*[A-Za-z-0-9]*");
    }
}
