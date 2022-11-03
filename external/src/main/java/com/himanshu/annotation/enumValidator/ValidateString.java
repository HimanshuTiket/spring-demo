package com.himanshu.annotation.enumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint( validatedBy = ValidateStringImpl.class)
public @interface ValidateString {

    String[] acceptedValues();

    String message() default "REST api method not supported !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
