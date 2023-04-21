package ru.taratonov.shoppingcart.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.taratonov.shoppingcart.enumerations.PaymentMethod;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumPaymentMethodValidator.class)
public @interface EnumPaymentMethod {
    PaymentMethod[] anyOf();

    String message() default "must be any of {anyOf}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
