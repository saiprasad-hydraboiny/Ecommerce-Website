package com.ecommerce.orderservice.service;


import com.ecommerce.orderservice.dto.InventoryResponse;
import com.ecommerce.orderservice.dto.OrderLineItemsDto;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderLineItems;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRespository;
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequest orderRequest)
    {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems>orderLineItems=orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrdeLineItemsList(orderLineItems);
        //Call Inventory Service and check whether the product is in stock or not
        List<String>skuCodes=order.getOrdeLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponses=webClientBuilder.build().get().uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build()).retrieve().bodyToMono(InventoryResponse[].class).block();

        assert inventoryResponses != null;
        boolean allProductsIsStock= Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if(allProductsIsStock)
        {
            orderRespository.save(order);
            log.info("order Placed");
            return "Order placed successfully";

        }
        else throw new IllegalArgumentException("Product is not in stock, please try again");
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }

}
