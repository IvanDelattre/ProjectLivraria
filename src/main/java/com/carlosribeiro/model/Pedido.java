package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

import java.util.Date;
import java.util.List;

public class Pedido {
    @Id
    private int id;
    private Date dataEmissao;
    private Date dataCancelamento;
    private String status;
    private List<ItemPedido> itensPedidos ;

    public Pedido(int id, Date dataEmissao ,List<ItemPedido> itensPedidos ) {
        this.itensPedidos = itensPedidos;
        this.dataEmissao = dataEmissao;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataEmissao=" + dataEmissao +
                ", dataCancelamento=" + dataCancelamento +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public String getStatus() {
        return status;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
