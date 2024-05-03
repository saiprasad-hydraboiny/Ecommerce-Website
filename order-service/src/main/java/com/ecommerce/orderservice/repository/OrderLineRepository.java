package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderLineRepository extends JpaRepository<OrderLineItems,Long> {
    OrderLineItems getOrderLineItemsById(Long id);
}
