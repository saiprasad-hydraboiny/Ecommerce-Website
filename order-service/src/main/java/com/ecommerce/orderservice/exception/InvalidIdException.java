package com.ecommerce.orderservice.exception;

public class InvalidIdException extends RuntimeException{

    public InvalidIdException(String message){
        super(message);
    }

}
