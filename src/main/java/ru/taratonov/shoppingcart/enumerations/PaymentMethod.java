package ru.taratonov.shoppingcart.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum PaymentMethod {
    CREDIT_CARD("credit card"),
    PAY_PAL("pay pal"),
    ON_RECEIPT("receipt"),
    UNKNOWN("unknown");
    @JsonCreator
    static PaymentMethod findValue(String findValue){
        return Arrays.stream(PaymentMethod.values())
                .filter(value->value.name().equals(findValue))
                .findFirst()
                .orElse(PaymentMethod.UNKNOWN);
    }

    private String title;

    PaymentMethod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
