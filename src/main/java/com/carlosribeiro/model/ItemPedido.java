package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

import java.io.Serializable;

public class ItemPedido implements Serializable {
    @Id
    private int id;
    private int qtdPedida;
    private int qtdAfaturar;
    private double precoCobrado;
    private Livro livro;
    Pedido pedido;

    public ItemPedido(int qtdPedida, double precoCobrado, Livro livro) {
        this.qtdPedida = qtdPedida;
        this.qtdAfaturar = qtdPedida;
        this.precoCobrado = precoCobrado;
        this.livro = livro;
    }


    @Override
    public String toString() {
        return "ItemPedido " +
                "id = " + id +
                ", qtdPedida = " + qtdPedida +
                ", qtdAfaturar = " + qtdAfaturar +
                ", precoCobrado = " + precoCobrado +
                ", livro = " + livro +
                ", pedido = " + pedido +
                '}';
    }

    public int getQtdAfaturar() {
        return qtdAfaturar;
    }

    public int getQtdPedida() {
        return qtdPedida;
    }

    public double getPrecoCobrado() {
        return precoCobrado;
    }

    public Livro getLivro() {
        return livro;
    }

    public Pedido getPedido() {
        return pedido;
    }


    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setQtdAfaturar(int qtdAfaturar) {
        this.qtdAfaturar = qtdAfaturar;
    }

    public void setQtdPedida(int qtdPedida) {
        this.qtdPedida = qtdPedida;
    }

    public void setPrecoCobrado(double precoCobrado) {
        this.precoCobrado = precoCobrado;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
