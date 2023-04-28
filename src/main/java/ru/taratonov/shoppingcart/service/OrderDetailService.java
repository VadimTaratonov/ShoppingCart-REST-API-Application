package ru.taratonov.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.taratonov.shoppingcart.exception.OrderDetailNotFoundException;
import ru.taratonov.shoppingcart.exception.OrderNotFoundException;
import ru.taratonov.shoppingcart.exception.ProductIsNotInStockException;
import ru.taratonov.shoppingcart.model.Order;
import ru.taratonov.shoppingcart.model.OrderDetail;
import ru.taratonov.shoppingcart.model.Product;
import ru.taratonov.shoppingcart.repository.OrderDetailRepository;
import ru.taratonov.shoppingcart.repository.OrderRepository;
import ru.taratonov.shoppingcart.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderDetail> getAll(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw OrderNotFoundException.createWith(id);
        }
        return optionalOrder.get().getOrderDetailList();
    }

    @Transactional
    public void saveOrderDetail(OrderDetail orderDetail) {
        Product product = orderDetail.getProduct();
        int id = product.getId();
        Product realProductWithIdInDb = productRepository.findById(id).get();
        boolean inStock = realProductWithIdInDb.isInStock();
        if (inStock) {
            orderDetailRepository.save(orderDetail);
        } else throw ProductIsNotInStockException.createWith(realProductWithIdInDb);
    }

    @Transactional
    public void delete(int id) {
        Optional<OrderDetail> foundOrderDetail = orderDetailRepository.findById(id);
        if (foundOrderDetail.isEmpty()) {
            throw OrderDetailNotFoundException.createWith(id);
        }
        Order order = foundOrderDetail.get().getOrder();
        order.setOrderDetailList(order.getOrderDetailList().stream().filter(dl -> !dl.equals(foundOrderDetail.get())).toList());
        orderDetailRepository.deleteById(id);
    }
}
