package com.ecommerce.orderservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;



@Data
@RequiredArgsConstructor
public class OrderLineItemsDto {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
