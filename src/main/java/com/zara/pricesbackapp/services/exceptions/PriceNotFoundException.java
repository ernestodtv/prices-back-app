package com.zara.pricesbackapp.services.exceptions;

public class PriceNotFoundException  extends RuntimeException {

    public PriceNotFoundException(String message) {
        super(message);
    }

}
