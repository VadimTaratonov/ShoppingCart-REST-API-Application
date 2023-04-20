package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import ru.taratonov.shoppingcart.enumerations.OrderStatus;
import ru.taratonov.shoppingcart.enumerations.PaymentMethod;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    @NotEmpty
    private Date orderDate;
    @NotEmpty
    private OrderStatus orderStatus;
    @NotEmpty
    private PaymentMethod paymentMethod;
    @NotEmpty
    private CustomerDTO customerDTO;

    private List<OrderDetailDTO> orderDetailDTO;

}
