package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

import java.io.Serializable;

public class ItemFaturado implements Serializable {
    @Id
    public int id;
    private int qtdFaturada;
    private ItemPedido itemPedido;

    public ItemFaturado(int qtdFaturada, ItemPedido itemPedido) {
        this.qtdFaturada = qtdFaturada;
        this.itemPedido = itemPedido;
    }

    @Override
    public String toString() {
        return "ItemFaturado " +
                "id = " + id +
                ", qtdFaturada = " + qtdFaturada +
                ", itemPedido = " + itemPedido + "\n" ;
    }

    public int getId() {
        return id;
    }

    public int getQtdFaturada() {
        return qtdFaturada;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setQtdFaturada(int qtdFaturada) {
        this.qtdFaturada = qtdFaturada;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }
}
