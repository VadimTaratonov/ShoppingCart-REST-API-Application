package ru.taratonov.shoppingcart.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.taratonov.shoppingcart.dto.OrderDetailDTO;
import ru.taratonov.shoppingcart.dto.OrderDetailDTORequestCreate;
import ru.taratonov.shoppingcart.model.OrderDetail;

@Mapper(uses = ProductMapper.class)
public interface OrderDetailMapper {

    OrderDetailMapper ORDER_DETAIL_MAPPER = Mappers.getMapper(OrderDetailMapper.class);

    @Mapping(source = "productDTO", target = "product")
    OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO);

    @InheritInverseConfiguration
    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "orderId", target = "order.id")
    OrderDetail toOrderDetail(OrderDetailDTORequestCreate orderDetailDTORequestCreate);
}
