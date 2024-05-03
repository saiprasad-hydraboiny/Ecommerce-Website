package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.OrderLineItemsDto;
import com.ecommerce.orderservice.exception.InvalidIdException;
import com.ecommerce.orderservice.exception.OrderNotFoundException;
import com.ecommerce.orderservice.model.OrderLineItems;
import com.ecommerce.orderservice.repository.OrderLineRepository;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock //We need mock object of the given attribute
    private OrderLineRepository orderLineRepository;

    @InjectMocks //this is the class under test and this is where the mock objects are injected
    private OrderService orderService;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testFindOrderLineItemsSuccess() throws OrderNotFoundException{
        //Arrange
        Long mockId=(long)1;
        OrderLineItems mockOrderLineItems=new OrderLineItems();
        mockOrderLineItems.setId(mockId);
        mockOrderLineItems.setQuantity(3);
        mockOrderLineItems.setSkuCode("test Order");
        mockOrderLineItems.setPrice(BigDecimal.valueOf(33));
        when(orderLineRepository.getOrderLineItemsById(mockId)).thenReturn(mockOrderLineItems);
        //Act
        OrderLineItemsDto mockResponse=orderService.findOrderLineItem(mockId);
        //Assert
        Assertions.assertEquals(mockResponse.getId(),mockOrderLineItems.getId());
        Assertions.assertEquals(mockResponse.getPrice(),mockOrderLineItems.getPrice());
        Assertions.assertEquals(mockResponse.getQuantity(),mockOrderLineItems.getQuantity());
        Assertions.assertEquals(mockResponse.getSkuCode(),mockOrderLineItems.getSkuCode());
    }


    @Test
    public void  testFindOrderLineItem_RepoReturnsNullObject() throws OrderNotFoundException{

        //Arrange
        Long mockId=null;
        OrderLineItems mockOrderLineItems=new OrderLineItems();
        mockOrderLineItems.setId(mockId);
        mockOrderLineItems.setQuantity(3);
        mockOrderLineItems.setSkuCode("test Order");
        mockOrderLineItems.setPrice(BigDecimal.valueOf(33));

        //Act & Assert
        Assertions.assertThrows(InvalidIdException.class,() -> orderService.findOrderLineItem(mockId)); //Asserting the thrown exception here

    }


    @Test
    public void testFindOrderLineItem_NullIdAsInput()
    {
        //Arrange
        Long mockId=(long)1;
        when(orderLineRepository.getOrderLineItemsById(mockId)).thenReturn(null);

        //Act & Assert
        Assertions.assertThrows(OrderNotFoundException.class,()->orderService.findOrderLineItem(mockId));


    }



}
