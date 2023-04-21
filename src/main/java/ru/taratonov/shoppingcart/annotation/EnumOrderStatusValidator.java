package ru.taratonov.shoppingcart.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.taratonov.shoppingcart.enumerations.OrderStatus;

import java.util.Arrays;


public class EnumOrderStatusValidator implements ConstraintValidator<EnumOrderStatus, OrderStatus> {
    private OrderStatus[] subset;

    @Override
    public void initialize(EnumOrderStatus constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(OrderStatus value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}