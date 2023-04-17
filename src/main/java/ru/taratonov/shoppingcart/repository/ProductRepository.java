package ru.taratonov.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taratonov.shoppingcart.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
