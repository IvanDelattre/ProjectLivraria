package com.carlosribeiro;

import com.carlosribeiro.dao.ClienteDAO;
import com.carlosribeiro.dao.LivroDAO;
import com.carlosribeiro.dao.PedidoDAO;
import com.carlosribeiro.exception.DataInvalidaException;
import com.carlosribeiro.model.Fatura;
import com.carlosribeiro.model.Livro;
import com.carlosribeiro.model.Pedido;
import com.carlosribeiro.util.FabricaDeDaos;
import corejava.Console;

import java.io.*;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {
        PedidoDAO pedidoDAO = FabricaDeDaos.getDAO(PedidoDAO.class) ;
        PrincipalLivro principalLivro= new PrincipalLivro();
        PrincipalCliente principalCliente= new PrincipalCliente();

    }

}