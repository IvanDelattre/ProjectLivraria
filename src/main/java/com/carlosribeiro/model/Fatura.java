package com.carlosribeiro.model;

import com.carlosribeiro.util.Id;

import java.util.Date;
import java.util.List;

public class Fatura {
    @Id
    //classe para faturar pedido;
    private int id;
    private Date dataEmissao;
    private Date dataCancelamento;
    private List<ItemFaturado> itensFaturados;

    public Fatura(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public int getId() {
        return id;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public List<ItemFaturado> getItensFaturados() {
        return itensFaturados;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public void setItensFaturados(List<ItemFaturado> itensFaturados) {
        this.itensFaturados = itensFaturados;
    }
}
