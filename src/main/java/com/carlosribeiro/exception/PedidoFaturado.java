package com.carlosribeiro.exception;

public class PedidoFaturado extends RuntimeException {
    public PedidoFaturado(String message) {
        super(message);
    }
}
