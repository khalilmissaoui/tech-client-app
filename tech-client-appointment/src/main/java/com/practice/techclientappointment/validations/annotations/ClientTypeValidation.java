package com.practice.techclientappointment.validations.annotations;

import com.practice.techclientappointment.validations.implementaions.ClientTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ClientTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientTypeValidation {
    String message() default "Client type shouLd end with TYPE.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}