package com.carlosribeiro.model;

import com.carlosribeiro.exception.DataInvalidaException;
import com.carlosribeiro.util.Id;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pedido implements Serializable {
    @Id
    private int id;
    private LocalDate dataEmissao;
    private LocalDate dataCancelamento;
    private double status;
    private List<ItemPedido> itensPedidos ;
    private Cliente cliente;

    private static final DateTimeFormatter DTF;

    static {
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public Pedido( String dataEmissao , List<ItemPedido> itensPedidos, Cliente cliente ) throws DataInvalidaException{
       setDataEmissao(dataEmissao);
       this.itensPedidos = itensPedidos;
       this.status = 0;
       this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "Pedido " +
                "id = " + id +
                ", dataEmissao = " + getDataAdmissaoMasc() +
                ", status = " + status + '\'' +
                ", dataCancelamento=" + (dataCancelamento != null ? getDataCancelamentoMasc() : "Não Cancelado")+
                ", ItensPedidos = " + itensPedidos +
                " ,Cliente = " + cliente.getNome();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public String getDataAdmissaoMasc() {
        return DTF.format(dataEmissao);
    }

    public String getDataCancelamentoMasc() {
        return DTF.format(dataCancelamento);
    }

    public double getStatus() {
        return status;
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


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }




    public void setStatus(double status) {
        this.status = status;
    }
}
