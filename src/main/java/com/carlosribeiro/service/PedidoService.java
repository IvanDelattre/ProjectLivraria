package com.carlosribeiro.service;

import com.carlosribeiro.dao.PedidoDAO;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.model.Pedido;
import com.carlosribeiro.util.FabricaDeDaos;

import java.util.List;

public class PedidoService {

    PedidoDAO pedidoDAO = FabricaDeDaos.getDAO(PedidoDAO.class);

    public Pedido incluir(Pedido pedido) {
        return pedidoDAO.incluir(pedido);
    }

    //todo adicionar regras de négocio
    public Pedido remover(int id) {
        Pedido pedido = recuperarPorId(id);
        pedido = pedidoDAO.remover(id);
        return pedido;
    }

    public Pedido recuperarPorId(Integer id) {
        Pedido pedido =  pedidoDAO.recuperarPorId(id);
        if(pedido == null){
            throw new EntidadeNaoEncontradaException("Pedido nao encontrado");
        }
        return pedido;
    }

    public List<Pedido> recuperarTodos(){
        return pedidoDAO.recuperarTodos();
    }


}
