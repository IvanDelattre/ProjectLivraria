package com.carlosribeiro.exception;

public class ImpossivelRemoverFatura extends RuntimeException {
    public ImpossivelRemoverFatura(String message) {
        super(message);
    }
}
