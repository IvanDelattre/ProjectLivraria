package com.carlosribeiro.model;

import com.carlosribeiro.exception.DataInvalidaException;
import com.carlosribeiro.util.Id;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Fatura {
    @Id
    //classe para faturar pedido;
    private int id;
    private LocalDate dataEmissao;
    private LocalDate dataCancelamento;
    private List<ItemFaturado> itensFaturados;
    private static final DateTimeFormatter DTF;

    static {
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }


    public Fatura(String dataEmissao , List<ItemFaturado> itensFaturados) throws DataInvalidaException {
        setDataEmissao(dataEmissao);
        this.itensFaturados = itensFaturados;
    }

    @Override
    public String toString() {
        return "Fatura " +
                "id = " + id +
                ", dataEmissao = " + getDataEmissaoMasc() +
                ", dataCancelamento = "+  (dataCancelamento != null ? getDataCancelamentoMasc() : "Não cancelado") +
                ", itensFaturados = " + itensFaturados ;
    }

    public int getId() {
        return id;
    }

    public String getDataEmissaoMasc() {
        return DTF.format(dataEmissao);
    }

    public String getDataCancelamentoMasc() {
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

    public void setItensFaturados(List<ItemFaturado> itensFaturados) {
        this.itensFaturados = itensFaturados;
    }
}
