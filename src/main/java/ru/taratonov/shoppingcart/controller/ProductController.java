package ru.taratonov.shoppingcart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taratonov.shoppingcart.dto.ProductDTO;
import ru.taratonov.shoppingcart.mapper.ProductMapper;
import ru.taratonov.shoppingcart.model.Product;
import ru.taratonov.shoppingcart.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product Controller", description = "Managing products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/available")
    @Operation(summary = "Get products", description = "Allows to get information about all in stock products")
    @ApiResponse(
            responseCode = "200",
            description = "Information received!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class)))
    public List<ProductDTO> getAllAvailableProducts() {
        return productService.getAllAvailableProducts().stream().map(this::convertToProductDTO).toList();
    }

    private ProductDTO convertToProductDTO(Product product) {
        return ProductMapper.PRODUCT_MAPPER.toProductDTO(product);
    }
}
