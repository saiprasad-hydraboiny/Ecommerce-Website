package com.ecommerce.productservice.dto;

import lombok.*;

import java.math.BigDecimal;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;

}
