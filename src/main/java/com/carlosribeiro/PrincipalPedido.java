package com.carlosribeiro;

import com.carlosribeiro.dao.ItemPedidoDAO;
import com.carlosribeiro.exception.DataInvalidaException;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.model.Cliente;
import com.carlosribeiro.model.ItemPedido;
import com.carlosribeiro.model.Livro;
import com.carlosribeiro.model.Pedido;
import com.carlosribeiro.service.ClienteService;
import com.carlosribeiro.service.LivroService;
import com.carlosribeiro.service.PedidoService;
import com.carlosribeiro.util.FabricaDeDaos;
import corejava.Console;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrincipalPedido {

    PedidoService pedidoService = new PedidoService();
    ClienteService clienteService = new ClienteService();
    LivroService livroService = new LivroService();
    ItemPedidoDAO itemPedidoDAO =  FabricaDeDaos.getDAO(ItemPedidoDAO.class );

    Cliente cliente;
    Pedido pedido;
    ItemPedido itemPedido;

    String dataEmissao;
    String dataCancelamento;
    int id;
    int idCliente;
    public void principal(){

        boolean continua  = true;
        while(continua){

            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um Pedido");
            System.out.println("2. Remover um Pedido");
            System.out.println("3. Listar todos os Pedidos");
            System.out.println("4. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
            switch(opcao){

                case 1-> {
                    int cadastro = 1;
                    List<ItemPedido> itensPedidos = new ArrayList<>();
                    System.out.println('\n' + "========================================================");
                    idCliente = Console.readInt("Digite Id do cliente que realiza o  Pedido: ");
                    try{
                        cliente = clienteService.recuperarPorId(idCliente);
                    }catch(EntidadeNaoEncontradaException e){
                        System.out.println("\n" + e.getMessage());
                        break;
                    }

                    while( cadastro == 1 ){

                        id = Console.readInt("Digite Id do Livro pedido: ") ;
                        try{
                            //todo modificar qtdAFaturar de ItemDePedido
                            Livro livro = livroService.recuperarPorId(id);
                            int quantidade = Console.readInt("Digite quantidade do Livro Pedido: ");
                            itemPedido = new ItemPedido(quantidade , livro.getPreco()  , livro);
                            itensPedidos.add(itemPedido);

                        }catch(EntidadeNaoEncontradaException e){
                            System.out.println("\n" + e.getMessage());
                            continue;
                        }

                        do{
                            cadastro = Console.readInt("Deseja Cadastrar Outro Livro? [s/n] = [1/0]: ");
                            if(cadastro != 1 && cadastro != 0 ) System.out.println('\n'+"Opcao invalida - Digitar 1(continuar) ou 0(sair)");

                        }while(cadastro != 1 && cadastro != 0 );


                    }
                    if(!itensPedidos.isEmpty()){
                        try{
                            dataEmissao = Console.readLine("Digite data de emissão [dd/mm/yyyy]: ");
                            pedido = new Pedido(dataEmissao , itensPedidos , cliente);
                            for(ItemPedido i : itensPedidos){
                                itemPedidoDAO.incluir(i);
                            }
                            //itemPedidoDAO.incluir(itemPedido); //inclui itempedido no dao
                            pedidoService.incluir(pedido); //inclui pedido no service
                            cliente.getPedidos().add(pedido); //pedido no list de cliente
                            System.out.println("Pedido " + pedido.getId() +  " cadastrado com sucesso!");

                        }catch(DataInvalidaException e){
                            System.out.println('\n' + e.getMessage() + "Pedido descartado" );
                        }
                    }

                }
                case 3 ->{
                    List<Pedido> pedidos = pedidoService.recuperarTodos() ;
                    for(Pedido pedido : pedidos){
                        System.out.println(pedido);
                    }

                }

                case 4 ->{
                    continua = false;
                }
                default -> System.out.println("Opção Invalida");
            }


        }


    }

}
