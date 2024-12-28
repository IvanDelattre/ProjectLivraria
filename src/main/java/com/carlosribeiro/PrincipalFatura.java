package com.carlosribeiro;

import com.carlosribeiro.dao.FaturaDAO;
import com.carlosribeiro.dao.ItemFaturadoDAO;
import com.carlosribeiro.exception.DataInvalidaException;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.exception.ImpossivelFaturar;
import com.carlosribeiro.exception.PedidoFaturado;
import com.carlosribeiro.model.Cliente;
import com.carlosribeiro.model.Fatura;
import com.carlosribeiro.model.ItemFaturado;
import com.carlosribeiro.model.Pedido;
import com.carlosribeiro.service.ClienteService;
import com.carlosribeiro.service.FaturaService;
import com.carlosribeiro.service.PedidoService;
import com.carlosribeiro.util.FabricaDeDaos;
import corejava.Console;

import java.util.ArrayList;
import java.util.List;

public class PrincipalFatura {

    private final FaturaService faturaService = new FaturaService();
    ClienteService clienteService = new ClienteService();
    PedidoService pedidoService = new PedidoService();
    ItemFaturadoDAO itemFaturadoDAO = FabricaDeDaos.getDAO(ItemFaturadoDAO.class);
    //chamando item faturadoDao ;

    String dataEmissao;
    String dataCancelamento;
    Cliente cliente;
    int idCliente;
    Pedido pedido;
    int idPedido;


    public void principal(){
        boolean continua = true;
        while (continua){

            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Faturar um pedido");
            System.out.println("2. Remover uma Fatura");
            System.out.println("3. Listar todas as Faturas");
            System.out.println("4. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
            switch (opcao){
                case 1 ->{
                    List <ItemFaturado> itensFaturados = new ArrayList<>();
                    idPedido = Console.readInt("Digite Id do pedido que deseja Faturar :");
                    try{
                        pedido = pedidoService.recuperarPorId(idPedido);
                    }catch(EntidadeNaoEncontradaException e){
                        System.out.println("\n" + e.getMessage());
                        break ;
                    }
                    try{
                        dataEmissao = Console.readLine("Digite data de emissão [dd/mm/yyyy]: ");
                        itensFaturados = faturaService.faturarPedido(itensFaturados , pedido) ;

                        Fatura fatura = new Fatura(dataEmissao , itensFaturados) ;

                        for(ItemFaturado itemFaturado : itensFaturados){
                            itemFaturadoDAO.incluir(itemFaturado);
                        }

                        faturaService.incluir(fatura);
                        pedido.getCliente().getFaturas().add(fatura);
                        System.out.println("Pedido Faturado");

                    }catch(DataInvalidaException | ImpossivelFaturar | PedidoFaturado e   ){
                        System.out.println("\n" + e.getMessage());
                        break ;
                    }


                }
                case 3 ->{
                    List<Fatura> faturas = faturaService.recuperarTodos() ;
                    for(Fatura fatura : faturas){
                        System.out.println(fatura);
                    }

                }

                case 4 ->{
                    continua = false;
                }


                default -> System.out.println("Opção inválida");

            }




        }

    }

}
