package com.carlosribeiro.util;

import com.carlosribeiro.model.Cliente;

public class Tarefa extends Thread {
    Cliente cliente;

    public Tarefa(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        System.out.println("\nEnviando email para " + cliente.getEmail());
    }

}
