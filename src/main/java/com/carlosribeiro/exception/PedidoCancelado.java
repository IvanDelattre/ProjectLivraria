package com.carlosribeiro.exception;

public class PedidoCancelado extends RuntimeException {
    public PedidoCancelado(String message) {
        super(message);
    }
}
