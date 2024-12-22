package com.carlosribeiro;

import com.carlosribeiro.dao.ClienteDAO;
import com.carlosribeiro.dao.ItemPedidoDAO;
import com.carlosribeiro.dao.LivroDAO;
import com.carlosribeiro.dao.PedidoDAO;
import com.carlosribeiro.exception.DataInvalidaException;
import com.carlosribeiro.model.Fatura;
import com.carlosribeiro.model.ItemPedido;
import com.carlosribeiro.model.Livro;
import com.carlosribeiro.model.Pedido;
import com.carlosribeiro.util.FabricaDeDaos;
import corejava.Console;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {

        PrincipalLivro principalLivro= new PrincipalLivro();
        PrincipalCliente principalCliente= new PrincipalCliente();
        PrincipalPedido principalPedido= new PrincipalPedido();

        //recuperarDados();

        boolean continua  = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Livros");
            System.out.println("2. Tratar Clientes");
            System.out.println("3. Tratar Pedidos");
            System.out.println("4. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 3:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    try{
                        principalLivro.principal();

                    }catch (Exception e){
                        System.out.println('\n' + "Erro: " + e.getMessage());
                    }

                }
                case 2 -> {
                    principalCliente.principal();
                }
                case 3 -> {
                    principalPedido.principal();
                }

                case 4 -> {
                    //salvarDados();
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
        ItemPedidoDAO itemPedidoDAO = FabricaDeDaos.getDAO(ItemPedidoDAO.class);
        List<ItemPedido> intens = itemPedidoDAO.recuperarTodos();
        for (ItemPedido itemPedido : intens) {
            System.out.println(itemPedido);
        }


      }

}