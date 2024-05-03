package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.OrderLineItemsDto;
import com.ecommerce.orderservice.model.OrderLineItems;

public class OrderLineItemMapper {
    static public OrderLineItemsDto orderLineItemsToDto(OrderLineItems orderLineItems)
    {
        OrderLineItemsDto orderLineItemsDto=new OrderLineItemsDto();
        orderLineItemsDto.setId(orderLineItems.getId());
        orderLineItemsDto.setPrice(orderLineItems.getPrice());
        orderLineItemsDto.setQuantity(orderLineItems.getQuantity());
        orderLineItemsDto.setSkuCode(orderLineItems.getSkuCode());

        return orderLineItemsDto;
    }
}
