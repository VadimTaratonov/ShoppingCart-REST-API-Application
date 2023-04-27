package ru.taratonov.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.taratonov.shoppingcart.model.Order;
import ru.taratonov.shoppingcart.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class Scheduler {
    static final Logger LOGGER = Logger.getLogger(Scheduler.class.getName());
    private final OrderRepository orderRepository;
    @Value("${money.sum}")
    private int moneySum;
    private LocalDate localDate;

    @Autowired
    public Scheduler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Scheduled(cron = "${cron.interval}")
    public void getOrders() {
        localDate = LocalDate.now();
        List<Order> allByOrderDate = orderRepository.findAllByOrderDate(localDate);
        if (allByOrderDate.isEmpty()) {
            LOGGER.info("No orders at the specified time");
        } else {
            allByOrderDate.stream()
                    .filter(o -> o.getOrderDetailList().stream()
                            .mapToInt(od -> (od.getPrice() * od.getQuantity()))
                            .sum() >= moneySum)
                    .forEach(o -> LOGGER.info("Order with id " + o.getId() + " was made " + localDate
                            + " and price more than " + moneySum));

        }
    }
}
