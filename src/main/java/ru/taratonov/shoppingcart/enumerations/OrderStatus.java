package ru.taratonov.shoppingcart.enumerations;

public enum OrderStatus {
    CREATED("created"),
    PROCESSING("processing"),
    CANCELLED("cancelled"),
    SHIPPED("shipped");

    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
