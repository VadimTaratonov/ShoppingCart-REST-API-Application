package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    @NotEmpty
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    private int price;

    @NotEmpty
    private String description;

    @NotEmpty
    private boolean inStock;

}
