package com.carlosribeiro.service;

import com.carlosribeiro.dao.FaturaDAO;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.model.Fatura;
import com.carlosribeiro.util.FabricaDeDaos;

public class FaturaService {
    FaturaDAO faturaDAO = FabricaDeDaos.getDAO(FaturaDAO.class);

    public Fatura incluir(Fatura fatura) {
        return faturaDAO.incluir(fatura);
    }

    //todo adicionar regras de négocios
    public Fatura remover(int id) {
        Fatura fatura = recuperarPorId(id);
        fatura = faturaDAO.remover(id);
        return fatura;
    }


    public Fatura recuperarPorId(Integer id) {
        Fatura fatura = faturaDAO.recuperarPorId(id);
        if(fatura == null) {
            throw new EntidadeNaoEncontradaException("Fatura nao encontrada");
        }
        return fatura;
    }



}
