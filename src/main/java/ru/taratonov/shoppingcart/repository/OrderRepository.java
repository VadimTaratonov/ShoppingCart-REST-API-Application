package ru.taratonov.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taratonov.shoppingcart.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
