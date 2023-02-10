package com.practice.techclientappointment.validations.implementaions;

import com.practice.techclientappointment.validations.annotations.ContactNumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements
        ConstraintValidator<ContactNumberValidation, String> {

    @Override
    public void initialize(ContactNumberValidation contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]+")
                && (contactField.length() > 8) && (contactField.length() < 14);
    }

}