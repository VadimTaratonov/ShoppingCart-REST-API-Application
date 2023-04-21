package ru.taratonov.shoppingcart.controller;

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
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/available")
    public List<ProductDTO> getAllAvailableProducts() {
        return productService.getAllAvailableProducts().stream().map(this::convertToProductDTO).toList();
    }

    private ProductDTO convertToProductDTO(Product product) {
        return ProductMapper.PRODUCT_MAPPER.toProductDTO(product);
    }
}
