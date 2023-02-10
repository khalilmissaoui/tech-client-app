package com.practice.techclientappointment.validations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public interface ValidateEntity<T> {

    // Validate generic objects and throws and RTE in case of invalidity
    default void validate(T objectToValidate) throws RuntimeException {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);

        if (!violations.isEmpty()) {
            throw new RuntimeException("---------------------+++++++++++ unvalidated object");
        }
    }
}
