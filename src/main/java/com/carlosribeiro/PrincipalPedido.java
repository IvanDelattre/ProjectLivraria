package com.carlosribeiro;

import com.carlosribeiro.model.ItemPedido;
import com.carlosribeiro.service.ClienteService;
import com.carlosribeiro.service.LivroService;
import com.carlosribeiro.service.PedidoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrincipalPedido {

    PedidoService pedidoService = new PedidoService();
    ClienteService clienteService = new ClienteService();
    LivroService livroService = new LivroService();

    List<ItemPedido> itensPedidos = new ArrayList<>();
    Date dataEmissao;
    

    public void Peincipal(){

    }

}
