package com.carlosribeiro;

import com.carlosribeiro.dao.ClienteDAO;
import com.carlosribeiro.dao.ItemPedidoDAO;
import com.carlosribeiro.dao.LivroDAO;
import com.carlosribeiro.dao.PedidoDAO;
import com.carlosribeiro.exception.DataInvalidaException;
import com.carlosribeiro.model.*;
import com.carlosribeiro.util.FabricaDeDaos;
import corejava.Console;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {

        PrincipalLivro principalLivro= new PrincipalLivro();
        PrincipalCliente principalCliente= new PrincipalCliente();
        PrincipalPedido principalPedido= new PrincipalPedido();
        PrincipalFatura principalFatura= new PrincipalFatura();

        recuperarDados();

        boolean continua  = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Livros");
            System.out.println("2. Tratar Clientes");
            System.out.println("3. Tratar Pedidos");
            System.out.println("4. Tratar Faturas");
            System.out.println("5. Sair");

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
                case 4 ->{
                    principalFatura.principal();
                }

                case 5 -> {
                    salvarDados();
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
    private static void salvarDados() {
        LivroDAO livroDAO = FabricaDeDaos.getDAO(LivroDAO.class);
        ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
        PedidoDAO pedidoDAO = FabricaDeDaos.getDAO(PedidoDAO.class);
        ItemPedidoDAO itemPedidoDAO = FabricaDeDaos.getDAO(ItemPedidoDAO.class);

        Map<Integer, Livro> mapDeLivro = livroDAO.getMap();
        int contadorLivros = livroDAO.getContador();

        Map<Integer, Cliente> mapDeCliente = clienteDAO.getMap();
        int contadorClientes = clienteDAO.getContador();

         Map<Integer, Pedido> mapDePedido = pedidoDAO.getMap();
        int contadorPedidos = pedidoDAO.getContador();

        Map<Integer, ItemPedido> mapDeItemPedido = itemPedidoDAO.getMap();
        int contadorItemPedidos = itemPedidoDAO.getContador();

        try {
            FileOutputStream fos = new FileOutputStream("arquivo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(mapDeLivro);
            oos.writeInt(contadorLivros);

            oos.writeObject(mapDeCliente);
            oos.writeInt(contadorClientes);

            oos.writeObject(mapDePedido);
            oos.writeInt(contadorPedidos);

            oos.writeObject(mapDeItemPedido);
            oos.writeInt(contadorItemPedidos);

            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recuperarDados() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("arquivo.dat");
            ois = new ObjectInputStream(fis);

            Map<Integer, Livro> mapDeLivro = (Map<Integer, Livro>) ois.readObject();
            int contadorLivros = ois.readInt();

            Map<Integer, Cliente> mapDeCliente = (Map<Integer, Cliente>) ois.readObject();
            int contadorClientes = ois.readInt();

            // Se houver outras entidades a serem recuperadas, mantenha a ordem correta de leitura
            Map<Integer, Pedido> mapDePedido = (Map<Integer, Pedido>) ois.readObject();
            int contadorPedidos = ois.readInt();

            Map<Integer, ItemPedido> mapDeItemPedido = (Map<Integer, ItemPedido>) ois.readObject();
            int contadorItemPedidos = ois.readInt();

            LivroDAO livroDAO = FabricaDeDaos.getDAO(LivroDAO.class);
            ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
            PedidoDAO pedidoDAO = FabricaDeDaos.getDAO(PedidoDAO.class);
            ItemPedidoDAO itemPedidoDAO = FabricaDeDaos.getDAO(ItemPedidoDAO.class);

            livroDAO.setMap(mapDeLivro);
            livroDAO.setContador(contadorLivros);

            clienteDAO.setMap(mapDeCliente);
            clienteDAO.setContador(contadorClientes);

            pedidoDAO.setMap(mapDePedido);
            pedidoDAO.setContador(contadorPedidos);

            itemPedidoDAO.setMap(mapDeItemPedido);
            itemPedidoDAO.setContador(contadorItemPedidos);

        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não existe e será criado.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                System.err.println("Erro ao fechar o stream: " + e.getMessage());
            }
        }
    }





}