package ru.taratonov.shoppingcart.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taratonov.shoppingcart.dto.OrderDetailDTO;
import ru.taratonov.shoppingcart.mapper.OrderDetailMapper;
import ru.taratonov.shoppingcart.model.OrderDetail;
import ru.taratonov.shoppingcart.service.OrderDetailService;


@RestController
@RequestMapping("/order_detail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createPerson(@RequestBody @Valid OrderDetailDTO orderDetailDTO) {

        orderDetailService.saveOrderDetail(convertToOrderDetail(orderDetailDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private OrderDetail convertToOrderDetail(OrderDetailDTO orderDetailDTO) {
        return OrderDetailMapper.ORDER_DETAIL_MAPPER.toOrderDetail(orderDetailDTO);
    }

    private OrderDetailDTO convertToOrderDetailDTO(OrderDetail orderDetail) {
        return OrderDetailMapper.ORDER_DETAIL_MAPPER.toOrderDetailDTO(orderDetail);
    }
}
