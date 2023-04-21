package ru.taratonov.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.taratonov.shoppingcart.model.Order;
import ru.taratonov.shoppingcart.model.OrderDetail;
import ru.taratonov.shoppingcart.model.Product;
import ru.taratonov.shoppingcart.repository.OrderDetailRepository;
import ru.taratonov.shoppingcart.repository.OrderRepository;
import ru.taratonov.shoppingcart.repository.ProductRepository;
import ru.taratonov.shoppingcart.util.OrderDetailNotFoundException;
import ru.taratonov.shoppingcart.util.OrderNotFoundException;
import ru.taratonov.shoppingcart.util.ProductIsNotInStockException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<OrderDetail> getAll(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException();
        }
        return optionalOrder.get().getOrderDetailList();
    }

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void saveOrderDetail(OrderDetail orderDetail) {
        Product product = orderDetail.getProduct();
        int id = product.getId();
        boolean inStock = productRepository.findById(id).get().isInStock();
        if (inStock)
            orderDetailRepository.save(orderDetail);
        else throw new ProductIsNotInStockException();
    }

    @Transactional
    public void delete(int id) {
        Optional<OrderDetail> foundOrderDetail = orderDetailRepository.findById(id);
        if (foundOrderDetail.isEmpty())
            throw new OrderDetailNotFoundException();
        Order order = foundOrderDetail.get().getOrder();
        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        List<OrderDetail> newOrderDetailList = new ArrayList<>();
        for (OrderDetail od : orderDetailList)
            if (!od.equals(foundOrderDetail.get()))
                newOrderDetailList.add(od);
        order.setOrderDetailList(newOrderDetailList);
        orderDetailRepository.deleteById(id);
    }
}
