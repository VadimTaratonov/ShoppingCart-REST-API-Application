package ru.taratonov.shoppingcart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.taratonov.shoppingcart.dto.OrderDTO;
import ru.taratonov.shoppingcart.model.Order;

@Mapper(uses = {OrderDetailMapper.class, CustomerMapper.class})
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "customerDTO", target = "customer")
    @Mapping(source = "orderDetailDTO", target = "orderDetailList")
    Order toOrder(OrderDTO order);

    @Mapping(source = "customer", target = "customerDTO")
    @Mapping(source = "orderDetailList", target = "orderDetailDTO")
    OrderDTO toOrderDTO(Order order);
}
