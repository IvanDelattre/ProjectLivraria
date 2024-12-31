package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Cliente implements Serializable {

    @Id
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private List<Pedido> pedidos;
    private List<Fatura> faturas ;
    private String endereco;

    public Cliente(String cpf, String nome, String email, String telefone , String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.pedidos = new ArrayList<>();
        this.faturas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Cliente " +
                " id= " + id +
                ", cpf = " + cpf + '\'' +
                ", nome = " + nome + '\'' +
                ", email = " + email + '\'' +
                ", endereço = " + endereco + '\'' +
                ", telefone = " + telefone + '\'' +
                ",\npedidos = "  +  pedidos + "\n" +
                "faturas = " + faturas  + "\n";
    }

    public int getId() {
        return id;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }



    public void setFaturas(List<Fatura> faturas) {
        this.faturas = faturas;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
