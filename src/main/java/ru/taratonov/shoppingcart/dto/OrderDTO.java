package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import ru.taratonov.shoppingcart.annotation.ValueOfEnum;
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
    @ValueOfEnum(enumClass = OrderStatus.class)
    private OrderStatus orderStatus;

    @NotEmpty
    @ValueOfEnum(enumClass = PaymentMethod.class)
    private PaymentMethod paymentMethod;

    @NotEmpty
    private CustomerDTO customerDTO;

    @NotEmpty
    private List<OrderDetailDTO> orderDetailDTO;

}
