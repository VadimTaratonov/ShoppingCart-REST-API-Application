package ru.taratonov.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taratonov.shoppingcart.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
