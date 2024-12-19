package com.carlosribeiro.model;

public class ItemFaturado {
    public int id;
    private int qtdFaturada;
    private ItemPedido itemPedido;

    public ItemFaturado(int qtdFaturada, ItemPedido itemPedido) {
        this.qtdFaturada = qtdFaturada;
        this.itemPedido = itemPedido;
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
