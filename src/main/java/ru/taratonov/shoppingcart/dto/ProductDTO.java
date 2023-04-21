package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    @NotEmpty(message = "name of product should not be empty")
    private String name;

    @NotEmpty
    @Min(0)
    private int price;

    @NotEmpty(message = "description should not be empty")
    private String description;

}
