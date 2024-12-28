package com.carlosribeiro.exception;

public class ImpossivelFaturar extends RuntimeException {
    public ImpossivelFaturar(String message) {
        super(message);
    }
}
