package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import ru.taratonov.shoppingcart.annotation.ValueOfEnum;
import ru.taratonov.shoppingcart.enumerations.OrderStatus;
import ru.taratonov.shoppingcart.enumerations.PaymentMethod;

import java.time.LocalDate;

@Getter
@Setter
public class OrderDTORequestCreate {

    @ValueOfEnum(enumClass = PaymentMethod.class)
    private PaymentMethod paymentMethod;

    @Min(1)
    private int customerId;
}
