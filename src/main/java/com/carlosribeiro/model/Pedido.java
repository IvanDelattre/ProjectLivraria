package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

import java.util.Date;

public class Pedido {
    @Id
    private int id;
    private Date data;
    private Date dataCancelamento;
    private String status;
    private ItemPedido itemPedido;

    public Pedido(int id, Date data) {
        this.id = id;
        this.data = data;
    }


    @Override
    public String toString() {
        return "Pedido" +
                "id=" + id +
                ", data=" + data +
                ", dataCancelamento=" + dataCancelamento +
                ", status=" + status +
                ", itemPedido=" + itemPedido
                ;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public String getStatus() {
        return status;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
