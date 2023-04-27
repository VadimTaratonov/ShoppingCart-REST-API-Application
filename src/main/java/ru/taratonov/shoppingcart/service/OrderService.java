package ru.taratonov.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.taratonov.shoppingcart.model.Order;
import ru.taratonov.shoppingcart.repository.OrderRepository;
import ru.taratonov.shoppingcart.exception.OrderNotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findOne(int id) {
        return orderRepository.findById(id).orElseThrow(() -> OrderNotFoundException.createWith(id));
    }

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrder(int id, Order updatedOrder) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        if (foundOrder.isEmpty()) {
            throw OrderNotFoundException.createWith(id);
        }
        updatedOrder.setId(id);
        updatedOrder.setOrderDate(foundOrder.get().getOrderDate());
        updatedOrder.setPaymentMethod(foundOrder.get().getPaymentMethod());
        updatedOrder.setOrderDetailList(foundOrder.get().getOrderDetailList());
        updatedOrder.setCustomer(foundOrder.get().getCustomer());
        orderRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        if (foundOrder.isEmpty()) {
            throw OrderNotFoundException.createWith(id);
        }
        orderRepository.deleteById(id);
    }
}
