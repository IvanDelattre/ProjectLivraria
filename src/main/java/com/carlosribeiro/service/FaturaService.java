package com.carlosribeiro.service;

import com.carlosribeiro.dao.FaturaDAO;
import com.carlosribeiro.dao.ItemFaturadoDAO;
import com.carlosribeiro.dao.ItemPedidoDAO;
import com.carlosribeiro.dao.PedidoDAO;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.exception.ImpossivelFaturar;
import com.carlosribeiro.exception.PedidoFaturado;
import com.carlosribeiro.model.*;
import com.carlosribeiro.util.FabricaDeDaos;

import java.util.List;

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


    public List<Fatura> recuperarTodos(){
        return faturaDAO.recuperarTodos() ;
    }


    public List<ItemFaturado> faturarPedido(List<ItemFaturado> itensFaturados ,Pedido pedido){

        ItemFaturadoDAO itemFaturadoDAO = FabricaDeDaos.getDAO(ItemFaturadoDAO.class);

        if(pedido.getStatus() == 1 ){
            throw new PedidoFaturado("Pedido Completamente Faturado") ;
        }
        pedido.setStatus(1); //
        List<ItemPedido> itensPedidos = pedido.getItensPedidos();
        for(ItemPedido itemPedido : itensPedidos) {

            if( itemPedido.getQtdAfaturar() == 0  ) continue ; //Esse item de pedido já foi faturado;

            if( itemPedido.getLivro().getQtdEstoque() >= itemPedido.getQtdAfaturar() ){
                Livro livro = itemPedido.getLivro();
                ItemFaturado itemFaturado = new ItemFaturado( itemPedido.getQtdAfaturar()   , itemPedido ) ;

                //itemFaturadoDAO.incluir(itemFaturado); - incluir no DAO nop main
                itensFaturados.add(itemFaturado);

                //criar o item faturado e tirar do estoque sem complicações
                int newEstoque = livro.getQtdEstoque() - itemPedido.getQtdAfaturar() ;
                livro.setQtdEstoque(newEstoque);
                itemPedido.setQtdAfaturar(0);
            }else  if(  itemPedido.getLivro().getQtdEstoque() != 0 && itemPedido.getLivro().getQtdEstoque() < itemPedido.getQtdAfaturar() ) {
                System.out.println("\nPedido não foi completamente Faturado\n");
                pedido.setStatus(0); //assim o pedido poderá ser faurado novamente
                Livro livro = itemPedido.getLivro();
                int estoque = livro.getQtdEstoque() ;
                itemPedido.setQtdAfaturar( itemPedido.getQtdAfaturar() - estoque );

                ItemFaturado itemFaturado = new ItemFaturado( livro.getQtdEstoque()  , itemPedido ) ;
                livro.setQtdEstoque(0);

                itensFaturados.add(itemFaturado);
            }

        }
        //estoque de tudo era 0;
        if(itensFaturados.isEmpty() ){
            pedido.setStatus(0);
            throw new ImpossivelFaturar("Sem nenhum estoque para Itens Pedidos , Fatira de pedido abortada") ;

        }

        return itensFaturados;
    }




}
