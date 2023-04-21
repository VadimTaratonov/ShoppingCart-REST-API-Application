package ru.taratonov.shoppingcart.dto;

import lombok.Getter;
import lombok.Setter;
import ru.taratonov.shoppingcart.annotation.ValueOfEnum;
import ru.taratonov.shoppingcart.enumerations.OrderStatus;

@Getter
@Setter
public class OrderDTORequestUpdate {

    @ValueOfEnum(enumClass = OrderStatus.class)
    private OrderStatus orderStatus;
}
