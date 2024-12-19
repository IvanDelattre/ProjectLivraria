package com.carlosribeiro.service;

import com.carlosribeiro.dao.PedidoDAO;
import com.carlosribeiro.util.FabricaDeDaos;

public class PedidoService {

    PedidoDAO pedidoDAO = FabricaDeDaos.getDAO(PedidoDAO.class);


}
