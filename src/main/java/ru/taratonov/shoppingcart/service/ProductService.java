package ru.taratonov.shoppingcart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.taratonov.shoppingcart.model.Product;
import ru.taratonov.shoppingcart.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllAvailableProducts() {
        List<Product> all = productRepository.findAll();
        List<Product> newList = new ArrayList<>();
        for (Product pr : all)
            if (pr.isInStock())
                newList.add(pr);
        return newList;
    }
}
