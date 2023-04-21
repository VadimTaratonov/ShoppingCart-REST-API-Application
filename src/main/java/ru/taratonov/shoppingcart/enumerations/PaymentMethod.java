package ru.taratonov.shoppingcart.enumerations;

public enum PaymentMethod {
    CREDIT_CARD("credit card"),
    PAY_PAL("pay pal"),
    ON_RECEIPT("receipt");

    private String title;

    PaymentMethod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
