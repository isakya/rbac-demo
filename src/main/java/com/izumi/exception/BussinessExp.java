package com.izumi.exception;

public class BussinessExp extends RuntimeException{
    public BussinessExp() {
    }

    public BussinessExp(String message) {
        super(message);
    }
}
