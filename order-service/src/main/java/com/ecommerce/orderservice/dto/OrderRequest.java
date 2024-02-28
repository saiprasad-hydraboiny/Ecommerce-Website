package com.ecommerce.orderservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;



@Data
@RequiredArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDto>orderLineItemsDtoList;
}
