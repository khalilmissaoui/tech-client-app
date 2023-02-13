package com.practice.techclientappointment.validations.annotations;


import com.practice.techclientappointment.validations.implementaions.ContactNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionMessageValidation {
    String message() default "Invalid exception message , add service name ex : [service name]SERVICE-[Exception reason]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
