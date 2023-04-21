package ru.taratonov.shoppingcart.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.taratonov.shoppingcart.enumerations.PaymentMethod;

import java.util.Arrays;

public class EnumPaymentMethodValidator implements ConstraintValidator<EnumPaymentMethod, PaymentMethod> {
    private PaymentMethod[] subset;

    @Override
    public void initialize(EnumPaymentMethod constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(PaymentMethod value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
