package ru.taratonov.shoppingcart.dto;

import lombok.Getter;
import lombok.Setter;
import ru.taratonov.shoppingcart.annotation.EnumOrderStatus;
import ru.taratonov.shoppingcart.enumerations.OrderStatus;

@Getter
@Setter
public class OrderDTORequestUpdate {

    @EnumOrderStatus(anyOf = {OrderStatus.CREATED, OrderStatus.PROCESSING, OrderStatus.SHIPPED, OrderStatus.CANCELLED})
    private OrderStatus orderStatus;
}
