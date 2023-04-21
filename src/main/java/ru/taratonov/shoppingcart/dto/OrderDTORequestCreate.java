package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import ru.taratonov.shoppingcart.annotation.EnumPaymentMethod;
import ru.taratonov.shoppingcart.enumerations.PaymentMethod;

@Getter
@Setter
public class OrderDTORequestCreate {

    @EnumPaymentMethod(anyOf = {PaymentMethod.ON_RECEIPT, PaymentMethod.CREDIT_CARD, PaymentMethod.PAY_PAL})
    private PaymentMethod paymentMethod;

    @Min(1)
    private int customerId;
}
