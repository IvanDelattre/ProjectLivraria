package com.carlosribeiro.exception;

public class FaturaJaCancelada extends RuntimeException {
    public FaturaJaCancelada(String message) {
        super(message);
    }
}
