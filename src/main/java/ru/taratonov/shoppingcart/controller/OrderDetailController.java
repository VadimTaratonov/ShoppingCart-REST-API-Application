package ru.taratonov.shoppingcart.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taratonov.shoppingcart.dto.OrderDetailDTO;
import ru.taratonov.shoppingcart.dto.OrderDetailDTORequestCreate;
import ru.taratonov.shoppingcart.mapper.OrderDetailMapper;
import ru.taratonov.shoppingcart.model.OrderDetail;
import ru.taratonov.shoppingcart.service.OrderDetailService;
import ru.taratonov.shoppingcart.util.ErrorResponse;
import ru.taratonov.shoppingcart.util.OrderDetailNotFoundException;
import ru.taratonov.shoppingcart.util.ProductIsNotInStockException;

import java.util.List;


@RestController
@RequestMapping("/order_detail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/by_orderId")
    public List<OrderDetailDTO> getAllByOrderId(@RequestParam(value = "orderId", required = false) int id) {
        return orderDetailService.getAll(id).stream().map(this::convertToOrderDetailDTO).toList();
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createPerson(@RequestBody @Valid
                                                   OrderDetailDTORequestCreate orderDetailDTORequestCreate) {

        orderDetailService.saveOrderDetail(convertToOrderDetail(orderDetailDTORequestCreate));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        orderDetailService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private OrderDetail convertToOrderDetail(OrderDetailDTORequestCreate orderDetailDTORequestCreate) {
        return OrderDetailMapper.ORDER_DETAIL_MAPPER.toOrderDetail(orderDetailDTORequestCreate);
    }

    private OrderDetailDTO convertToOrderDetailDTO(OrderDetail orderDetail) {
        return OrderDetailMapper.ORDER_DETAIL_MAPPER.toOrderDetailDTO(orderDetail);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(OrderDetailNotFoundException e) {
        ErrorResponse response = new
                ErrorResponse("OrderDetail not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(ProductIsNotInStockException e) {
        ErrorResponse response = new
                ErrorResponse("This product is not in stock");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
