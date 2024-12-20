package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

public class ItemPedido {
    @Id
    private int id;
    private int qtdPedida;
    private int qtdAfaturar;
    private double precoCobrado;
    private Livro livro;

    public ItemPedido(int qtdPedida, double precoCobrado, Livro livro) {
        this.qtdPedida = qtdPedida;
        this.precoCobrado = precoCobrado;
        this.livro = livro;
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
