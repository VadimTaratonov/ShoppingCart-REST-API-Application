package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class OrderDetailDTORequestCreate {

    @Min(1)
    private int price;

    @Min(1)
    private int quantity;

    @Min(1)
    private int productId;

    @Min(1)
    private int orderId;
}
