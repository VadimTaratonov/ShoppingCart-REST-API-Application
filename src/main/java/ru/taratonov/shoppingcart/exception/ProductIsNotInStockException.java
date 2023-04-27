package ru.taratonov.shoppingcart.exception;

import ru.taratonov.shoppingcart.model.Product;

public class ProductIsNotInStockException extends RuntimeException {
    private Product product;

    public static ProductIsNotInStockException createWith(Product product) {
        return new ProductIsNotInStockException(product);
    }

    private ProductIsNotInStockException(Product product) {
        this.product = product;
    }

    @Override
    public String getMessage() {
        return "Product '" + product.getId() + "' with name " + product.getName() + " not found";
    }
}
