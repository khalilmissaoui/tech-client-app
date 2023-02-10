package com.practice.techclientappointment.validations.annotations;

import com.practice.techclientappointment.validations.implementaions.ContactNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNumberValidation {
    String message() default "Invalid phone number , length between 8 & 14 , all numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}