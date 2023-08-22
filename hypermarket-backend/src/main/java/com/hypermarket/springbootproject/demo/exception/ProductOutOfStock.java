package com.hypermarket.springbootproject.demo.exception;

public class ProductOutOfStock extends RuntimeException {
    public ProductOutOfStock(String message) {
        super(message);
    }
}
