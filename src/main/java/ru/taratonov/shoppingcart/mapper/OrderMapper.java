package ru.taratonov.shoppingcart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.taratonov.shoppingcart.dto.OrderDTO;
import ru.taratonov.shoppingcart.model.Order;

@Mapper
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    Order toOrder(OrderDTO order);

    OrderDTO toOrderDTO(Order order);
}
