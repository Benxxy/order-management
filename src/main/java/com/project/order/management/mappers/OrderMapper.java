package com.project.order.management.mappers;

import com.project.order.management.data.OrderDTO;
import com.project.order.management.data.OrderDetailsDTO;
import com.project.order.management.data.entity.CustomerOrders;
import com.project.order.management.data.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(target = "orderId", source = "customerOrders.id"),
            @Mapping(target = "orderDetailsDTO", source = "orderDetails")
    })
    OrderDTO customerOrdersToOrderDTO(CustomerOrders customerOrders);

    @Mappings({
            @Mapping(target = "productName", source = "products.name"),
            @Mapping(target = "price", source = "products.price")
    })
    OrderDetailsDTO orderDetailsToOrderDetailsDTO(OrderDetails orderDetails);

    List<OrderDTO> customerOrdersListToOrderDTOList(List<CustomerOrders> customerOrders);
}
