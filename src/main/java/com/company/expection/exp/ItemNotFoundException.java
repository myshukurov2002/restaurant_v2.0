package com.company.expection.exp;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message) {
        super(message);
    }
    public ItemNotFoundException() {
    }
}
