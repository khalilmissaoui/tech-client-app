package com.practice.techclientappointment.validations.implementaions;

import com.practice.techclientappointment.validations.annotations.ClientTypeValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ClientTypeValidator implements
        ConstraintValidator<ClientTypeValidation, String> {

    @Override
    public void initialize(ClientTypeValidation clientType) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[A-Za-z-0-9- ]*TYPE$");
    }

}