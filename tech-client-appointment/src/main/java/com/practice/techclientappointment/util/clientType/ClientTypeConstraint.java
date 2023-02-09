package com.practice.techclientappointment.util.clientType;

import com.practice.techclientappointment.util.phoneNumber.ContactNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ClientTypeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientTypeConstraint {
    String message() default "Client type shouLd end with TYPE.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}