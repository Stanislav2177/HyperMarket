package com.hypermarket.springbootproject.demo.exception;

public class ManufacturerNotFoundException extends RuntimeException {

    public ManufacturerNotFoundException(String message){
        super(message);
    }
}
