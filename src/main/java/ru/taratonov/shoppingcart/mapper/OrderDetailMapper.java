package ru.taratonov.shoppingcart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.taratonov.shoppingcart.dto.OrderDetailDTO;
import ru.taratonov.shoppingcart.model.OrderDetail;

@Mapper
public interface OrderDetailMapper {

    OrderDetailMapper ORDER_DETAIL_MAPPER = Mappers.getMapper(OrderDetailMapper.class);

    OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);
}
