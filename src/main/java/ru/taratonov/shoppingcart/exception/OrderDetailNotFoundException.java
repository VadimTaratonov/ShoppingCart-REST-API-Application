package ru.taratonov.shoppingcart.exception;

public class OrderDetailNotFoundException extends RuntimeException {
    private int id;

    public static OrderDetailNotFoundException createWith(int id) {
        return new OrderDetailNotFoundException(id);
    }

    private OrderDetailNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "OrderDetail '" + id + "' not found";
    }
}
