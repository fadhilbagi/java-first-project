package com.example.demo.helper;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
