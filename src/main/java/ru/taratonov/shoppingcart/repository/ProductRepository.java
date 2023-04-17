package ru.taratonov.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taratonov.shoppingcart.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
