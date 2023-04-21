package ru.taratonov.shoppingcart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.taratonov.shoppingcart.dto.OrderDTO;
import ru.taratonov.shoppingcart.dto.OrderDTORequestCreate;
import ru.taratonov.shoppingcart.dto.OrderDTORequestUpdate;
import ru.taratonov.shoppingcart.model.Order;

import java.time.LocalDate;

@Mapper(uses = {OrderDetailMapper.class, CustomerMapper.class}, imports = LocalDate.class)
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "customer", target = "customerDTO")
    @Mapping(source = "orderDetailList", target = "orderDetailDTO")
    OrderDTO toOrderDTO(Order order);

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(target = "orderStatus", constant = "CREATED")
    @Mapping(target = "orderDate", expression = "java(LocalDate.now())")
    Order toOrder(OrderDTORequestCreate orderDTORequestCreate);


    Order toOrder(OrderDTORequestUpdate orderDTORequestUpdate);
}
