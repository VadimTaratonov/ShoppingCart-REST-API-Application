package ru.taratonov.shoppingcart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotEmpty(message = "surname should not be empty")
    private String surname;

    @NotEmpty(message = "address should not be empty")
    private String address;

    @Email(message = "email should be correct")
    @NotEmpty(message = "email should not be empty")
    private String email;

    @Min(0)
    private Long phone;
}
