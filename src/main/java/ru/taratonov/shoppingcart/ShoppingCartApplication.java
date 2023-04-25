package ru.taratonov.shoppingcart;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Shopping Cart REST Api",
                description = "The shopping cart application",
                version = "1.0.0",
                contact = @Contact(
                        name = "Taratonov Vadim",
                        email = "taratonovv8@bk.ru",
                        url = "https://github.com/VadimKiKi/ShoppingCart-REST-API-Application"
                )
        )
)

public class ShoppingCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }

}
