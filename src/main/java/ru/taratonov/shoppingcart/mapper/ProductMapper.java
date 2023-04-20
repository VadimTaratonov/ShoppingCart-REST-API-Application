package ru.taratonov.shoppingcart.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.taratonov.shoppingcart.dto.ProductDTO;
import ru.taratonov.shoppingcart.model.Product;

@Mapper()
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDTO productDTO);

    @InheritInverseConfiguration
    ProductDTO toProductDTO(Product product);
}
