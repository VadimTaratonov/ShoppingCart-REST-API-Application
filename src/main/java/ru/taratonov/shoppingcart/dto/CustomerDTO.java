package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import lombok.Setter;
@Getter
@Setter
public class CustomerDTO {
    @NotEmpty
    @Min(1)
    private int id;

    private String name;

    private String surname;

    private String address;

    private String email;

    @Min(0)
    private Long phone;

}
