package ru.taratonov.shoppingcart.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taratonov.shoppingcart.dto.OrderDTO;
import ru.taratonov.shoppingcart.mapper.OrderMapper;
import ru.taratonov.shoppingcart.model.Order;
import ru.taratonov.shoppingcart.service.OrderService;
import ru.taratonov.shoppingcart.util.ErrorResponse;
import ru.taratonov.shoppingcart.util.OrderNotFoundException;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable(name = "id") int id){
        return convertToOrderDTO(orderService.findOne(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createOrder(@RequestBody @Valid OrderDTO orderDTO) {

        orderService.saveOrder(convertToOrder(orderDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Order convertToOrder(OrderDTO orderDTO) {
        return OrderMapper.ORDER_MAPPER.toOrder(orderDTO);
    }

    private OrderDTO convertToOrderDTO(Order order) {
        return OrderMapper.ORDER_MAPPER.toOrderDTO(order);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(OrderNotFoundException e) {
        ErrorResponse response = new
                ErrorResponse("Order not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
