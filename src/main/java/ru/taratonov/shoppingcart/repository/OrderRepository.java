package ru.taratonov.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taratonov.shoppingcart.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
