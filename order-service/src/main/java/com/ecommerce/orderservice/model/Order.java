package com.ecommerce.orderservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;



@Entity
@Table(name="t_orders")
@Data
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderNumber;
    @OneToMany(cascade=CascadeType.ALL)
    private List<OrderLineItems>ordeLineItemsList;
}
