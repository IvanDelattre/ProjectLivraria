package com.carlosribeiro.service;

import com.carlosribeiro.dao.FaturaDAO;
import com.carlosribeiro.dao.ItemFaturadoDAO;
import com.carlosribeiro.dao.ItemPedidoDAO;
import com.carlosribeiro.dao.PedidoDAO;
import com.carlosribeiro.exception.*;
import com.carlosribeiro.model.*;
import com.carlosribeiro.util.FabricaDeDaos;

import java.util.List;

public class FaturaService {
    FaturaDAO faturaDAO = FabricaDeDaos.getDAO(FaturaDAO.class);

    public Fatura incluir(Fatura fatura) {
        return faturaDAO.incluir(fatura);
    }


    public void remover(int id , String dataCancelamento) {

        Fatura fatura = recuperarPorId(id);
        if(fatura.getDataCancelamentoMasc() != null ) {
            throw new FaturaJaCancelada("Error: Essa fatura já foi cancelada") ;
        }

        List<Pedido> pedidos = fatura.getPedido().getCliente().getPedidos() ;
        int cont = 0 ;
        for( Pedido p : pedidos  ){
            if( p.getStatus() == 1 ) cont = cont + 1;

        }
        if( cont < 3 ) throw new ImpossivelFaturar("error : Cliente Deve Faturar completamente ao menos 3 pedidos  para remover uma fatura") ;

        try{

            fatura.setDataCancelamento( dataCancelamento );
            fatura.getPedido().setStatus(0); // liberar pedido para ser faturado novamente ;
            List<ItemFaturado> itemFaturados = fatura.getItensFaturados();

            for(ItemFaturado i : itemFaturados) {
                Livro livro = i.getItemPedido().getLivro() ;
                int estoque = livro.getQtdEstoque();
                livro.setQtdEstoque( estoque + i.getQtdFaturada()   );
                int newQtdAFaturar = i.getQtdFaturada() + i.getItemPedido().getQtdAfaturar() ;
                i.getItemPedido().setQtdAfaturar(  newQtdAFaturar     );

                //exclusão em DAO correta
                ItemFaturadoDAO itemFaturadoDAO = FabricaDeDaos.getDAO(ItemFaturadoDAO.class);
                itemFaturadoDAO.remover(i.getId()) ;



            }

            //exclusão de itens faturados.
            fatura.getItensFaturados().clear();


            //fatura = faturaDAO.remover(id);

        }catch (DataInvalidaException e){
            System.out.println( '\n' + e.getMessage());
        }


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

        if( pedido.getDataCancelamentoMasc() != null ){
            throw new PedidoCancelado("Pedido Cancelado: Impossível de faturar pedido") ;

        }
        //todo:  se pedido já possui fatura , procurar a fatura já existente
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
                //itemFaturadoDAO.incluir(itemFaturado); - incluir no DAO no main
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
            throw new ImpossivelFaturar("Sem nenhum estoque para Itens Pedidos , Fatura de pedido abortada") ;

        }

        return itensFaturados;
    }

}
