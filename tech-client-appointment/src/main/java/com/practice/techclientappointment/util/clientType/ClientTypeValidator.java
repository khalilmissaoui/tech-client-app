package com.practice.techclientappointment.util.clientType;

import com.practice.techclientappointment.util.phoneNumber.ContactNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class ClientTypeValidator implements
        ConstraintValidator<ClientTypeConstraint, String> {

    @Override
    public void initialize(ClientTypeConstraint clientType) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[A-Za-z-0-9- ]*TYPE$");
    }

}