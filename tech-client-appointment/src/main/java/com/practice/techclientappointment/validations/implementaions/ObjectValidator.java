package com.practice.techclientappointment.validations.implementaions;

import com.practice.techclientappointment.exceptions.NotValidObjectException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectValidator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(Object objectToValidate) throws RuntimeException {


        Set<ConstraintViolation<Object>> violations = validator.validate(objectToValidate);

        if (!violations.isEmpty()) {
            var errorMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new NotValidObjectException(errorMessages);
        }
    }
}
