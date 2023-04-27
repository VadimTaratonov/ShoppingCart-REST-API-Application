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
import ru.taratonov.shoppingcart.dto.OrderDTO;
import ru.taratonov.shoppingcart.dto.OrderDTORequestCreate;
import ru.taratonov.shoppingcart.dto.OrderDTORequestUpdate;
import ru.taratonov.shoppingcart.mapper.OrderMapper;
import ru.taratonov.shoppingcart.model.Order;
import ru.taratonov.shoppingcart.service.OrderService;
import ru.taratonov.shoppingcart.exception.OrderNotFoundException;


@RestController
@RequestMapping("/order")
@Tag(name = "Order Controller", description = "Managing customer orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order", description = "Allows to get information about the order by id")
    @ApiResponse(
            responseCode = "200",
            description = "Information received!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Order not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)))
    public OrderDTO getOrder(@Parameter(description = "Id of the order", required = true)
                             @PathVariable(name = "id") int id) throws OrderNotFoundException {
        return convertToOrderDTO(orderService.findOne(id));
    }

    @PostMapping()
    @Operation(summary = "Post order", description = "Allows to post information about the order")
    @ApiResponse(
            responseCode = "200",
            description = "Information posted!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)))
    public ResponseEntity<HttpStatus> createOrder(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Order that needs to be added to the customer shopping cart",
            content = @Content(schema = @Schema(implementation = OrderDTORequestCreate.class)))
                                                  @RequestBody
                                                  @Valid OrderDTORequestCreate orderDTORequestCreate) {

        orderService.saveOrder(convertToOrder(orderDTORequestCreate));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update order", description = "Allows to update orderStatus in the order")
    @ApiResponse(
            responseCode = "200",
            description = "Information updated!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Order not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)))
    public ResponseEntity<HttpStatus> update(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Order that needs to be updated",
            content = @Content(schema = @Schema(implementation = OrderDTORequestUpdate.class)))
                                             @RequestBody
                                             @Valid OrderDTORequestUpdate orderDTORequestUpdate,
                                             @Parameter(description = "Id of the order", required = true)
                                             @PathVariable("id") int id) throws OrderNotFoundException {
        orderService.updateOrder(id, convertToOrder(orderDTORequestUpdate));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order", description = "Allows to delete order")
    @ApiResponse(
            responseCode = "200",
            description = "Order deleted!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Order not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)))
    public ResponseEntity<HttpStatus> delete(@Parameter(description = "Id of the order", required = true)
                                             @PathVariable("id") int id) throws OrderNotFoundException {
        orderService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Order convertToOrder(OrderDTORequestCreate orderDTORequestCreate) {
        return OrderMapper.ORDER_MAPPER.toOrder(orderDTORequestCreate);
    }

    private Order convertToOrder(OrderDTORequestUpdate orderDTORequestUpdate) {
        return OrderMapper.ORDER_MAPPER.toOrder(orderDTORequestUpdate);
    }

    private OrderDTO convertToOrderDTO(Order order) {
        return OrderMapper.ORDER_MAPPER.toOrderDTO(order);
    }
}
