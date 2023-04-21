package ru.taratonov.shoppingcart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.taratonov.shoppingcart.dto.CustomerDTO;
import ru.taratonov.shoppingcart.model.Customer;

@Mapper()
public interface CustomerMapper {

    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomer(CustomerDTO customerDTO);

    CustomerDTO toCustomerDTO(Customer customer);
}
