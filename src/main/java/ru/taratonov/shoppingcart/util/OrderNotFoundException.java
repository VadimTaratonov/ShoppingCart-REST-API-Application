package ru.taratonov.shoppingcart.util;

public class OrderNotFoundException extends RuntimeException {
    private int id;

    public static OrderNotFoundException createWith(int id) {
        return new OrderNotFoundException(id);
    }

    private OrderNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Order '" + id + "' not found";
    }
}
