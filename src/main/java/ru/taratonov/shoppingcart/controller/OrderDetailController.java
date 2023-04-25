package ru.taratonov.shoppingcart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taratonov.shoppingcart.dto.ErrorDTO;
import ru.taratonov.shoppingcart.dto.OrderDetailDTO;
import ru.taratonov.shoppingcart.dto.OrderDetailDTORequestCreate;
import ru.taratonov.shoppingcart.mapper.OrderDetailMapper;
import ru.taratonov.shoppingcart.model.OrderDetail;
import ru.taratonov.shoppingcart.service.OrderDetailService;
import ru.taratonov.shoppingcart.util.OrderDetailNotFoundException;
import ru.taratonov.shoppingcart.util.OrderNotFoundException;
import ru.taratonov.shoppingcart.util.ProductIsNotInStockException;

import java.util.List;


@RestController
@RequestMapping("/order_detail")
@Tag(name = "Order Detail Controller", description = "Managing order details of order")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/by_orderId")
    @Operation(summary = "Get order detail", description = "Allows to get information about the order detail by order id")
    @ApiResponse(
            responseCode = "200",
            description = "Information received!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDetailDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Order not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)))
    public List<OrderDetailDTO> getAllByOrderId(@Parameter(description = "Id of the order")
                                                @RequestParam(value = "orderId", required = false) int id)
            throws OrderNotFoundException {
        return orderDetailService.getAll(id).stream().map(this::convertToOrderDetailDTO).toList();
    }

    @PostMapping()
    @Operation(summary = "Post order detail", description = "Allows to post information about the order detail")
    @ApiResponse(
            responseCode = "200",
            description = "Information posted!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Product not in stock!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)))
    public ResponseEntity<HttpStatus> createPerson(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Order detail that needs to be added to the order",
            content = @Content(schema = @Schema(implementation = OrderDetailDTORequestCreate.class)))
                                                   @RequestBody
                                                   @Valid OrderDetailDTORequestCreate orderDetailDTORequestCreate)
            throws ProductIsNotInStockException {

        orderDetailService.saveOrderDetail(convertToOrderDetail(orderDetailDTORequestCreate));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order detail", description = "Allows to delete order detail")
    @ApiResponse(
            responseCode = "200",
            description = "Order detail deleted!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Order detail not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)))
    public ResponseEntity<HttpStatus> delete(@Parameter(description = "Id of the order detail", required = true)
                                             @PathVariable("id") int id) throws OrderDetailNotFoundException {
        orderDetailService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private OrderDetail convertToOrderDetail(OrderDetailDTORequestCreate orderDetailDTORequestCreate) {
        return OrderDetailMapper.ORDER_DETAIL_MAPPER.toOrderDetail(orderDetailDTORequestCreate);
    }

    private OrderDetailDTO convertToOrderDetailDTO(OrderDetail orderDetail) {
        return OrderDetailMapper.ORDER_DETAIL_MAPPER.toOrderDetailDTO(orderDetail);
    }
}
