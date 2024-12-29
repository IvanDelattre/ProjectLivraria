package com.carlosribeiro.model;

import com.carlosribeiro.exception.DataInvalidaException;
import com.carlosribeiro.util.Id;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Fatura implements Serializable {
    @Id
    //classe para faturar pedido;
    private int id;
    private LocalDate dataEmissao;
    private LocalDate dataCancelamento;
    private List<ItemFaturado> itensFaturados;
    private Cliente cliente;
    private static final DateTimeFormatter DTF;
    private double totalFaturado;


    private Pedido pedido;

    static {
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }


    public Fatura(String dataEmissao , List<ItemFaturado> itensFaturados , Pedido pedido ) throws DataInvalidaException {
        setDataEmissao(dataEmissao);
        this.itensFaturados = itensFaturados;
        this.pedido = pedido;

    }

    @Override
    public String toString() {
        return "Fatura " +
                "id = " + id +
                ", dataEmissao = " + getDataEmissaoMasc() +
                ", dataCancelamento = "+  (dataCancelamento != null ? getDataCancelamentoMasc() : "Não cancelado") +
                ", itensFaturados = " + itensFaturados +
                ", toalFaturado = " + totalFaturado +
                ", cliente = " + cliente;
    }

    public int getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotalFaturado() {
        return totalFaturado;
    }

    public String getDataEmissaoMasc() {
        return DTF.format(dataEmissao);
    }

    public String getDataCancelamentoMasc() {
        if(dataCancelamento == null)  return null ;

        return DTF.format(dataCancelamento) ;
    }

    public List<ItemFaturado> getItensFaturados() {
        return itensFaturados;
    }

    public void setDataEmissao (String novaDataAdmissao) throws DataInvalidaException {
        try {
            int dia = Integer.parseInt(novaDataAdmissao.substring(0, 2));
            int mes = Integer.parseInt(novaDataAdmissao.substring(3, 5));
            int ano = Integer.parseInt(novaDataAdmissao.substring(6, 10));

            dataEmissao = LocalDate.of(ano, mes, dia);
        }
        catch(StringIndexOutOfBoundsException |
              NumberFormatException |
              DateTimeException e) {
            throw new DataInvalidaException("Data inválida.");
        }
    }

    public void setDataCancelamento (String novaDataCancelamento) throws DataInvalidaException {
        try {
            int dia = Integer.parseInt(novaDataCancelamento.substring(0, 2));
            int mes = Integer.parseInt(novaDataCancelamento.substring(3, 5));
            int ano = Integer.parseInt(novaDataCancelamento.substring(6, 10));

            dataCancelamento = LocalDate.of(ano, mes, dia);
        }
        catch(StringIndexOutOfBoundsException |
              NumberFormatException |
              DateTimeException e) {
            throw new DataInvalidaException("Data inválida.");
        }
    }


    public void setTotalFaturado(){
        for( ItemFaturado itemFaturado : itensFaturados){
            totalFaturado += itemFaturado.getQtdFaturada() * itemFaturado.getItemPedido().getLivro().getPreco();
        }
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;


    }

    public void setItensFaturados(List<ItemFaturado> itensFaturados) {
        this.itensFaturados = itensFaturados;
    }
}
