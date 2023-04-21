package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import ru.taratonov.shoppingcart.annotation.EnumOrderStatus;
import ru.taratonov.shoppingcart.annotation.EnumPaymentMethod;
import ru.taratonov.shoppingcart.enumerations.OrderStatus;
import ru.taratonov.shoppingcart.enumerations.PaymentMethod;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    @NotEmpty
    private LocalDate orderDate;

    @NotEmpty
    @EnumOrderStatus(anyOf = {OrderStatus.CREATED, OrderStatus.PROCESSING, OrderStatus.SHIPPED, OrderStatus.CANCELLED})
    private OrderStatus orderStatus;

    @NotEmpty
    @EnumPaymentMethod(anyOf = {PaymentMethod.ON_RECEIPT, PaymentMethod.CREDIT_CARD, PaymentMethod.PAY_PAL})
    private PaymentMethod paymentMethod;

    @NotEmpty
    private CustomerDTO customerDTO;

    @NotEmpty
    private List<OrderDetailDTO> orderDetailDTO;

}
