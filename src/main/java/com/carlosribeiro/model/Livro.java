package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

public class Livro {
    @Id
    private int id;
    private String isbn;
    private String titulo;
    private String descricao;
    private int qtdEstoque ;
    private double preco;

    public Livro(String isbn, String titulo , String descricao , int qtdEstoque , double preco ){
        this.isbn = isbn;
        this.titulo = titulo;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.preco = preco;
    }


    @Override
    public String toString() {
        return "Livro " +
                "id = " + id +
                " ,isbn = " + isbn  +
                ", titulo = " + titulo  +
                ", descricao = " + descricao  +
                ", qtdEstoque = " + qtdEstoque +
                ", preco = " + preco;
    }

    public int getId() {
        return id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public double getPreco() {
        return preco;
    }




}
