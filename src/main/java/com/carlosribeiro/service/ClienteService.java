package com.carlosribeiro.service;

import com.carlosribeiro.dao.ClienteDAO;
import com.carlosribeiro.exception.ClienteAssociadoException;
import com.carlosribeiro.exception.EmUso;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.model.Cliente;
import com.carlosribeiro.util.FabricaDeDaos;

import java.util.List;

public class ClienteService {

    ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);


    public Cliente incluir(Cliente cliente) {
        clienteDAO.incluir(cliente);
        return cliente;
    }

    //todo regras de négocio
    public Cliente remover(int id ) {
        Cliente cliente = recuperarPorId(id) ;
        //remover se não tiver fatura ou pedidos ;
        if( cliente.getPedidos().isEmpty() && cliente.getFaturas().isEmpty() ){
            cliente = clienteDAO.remover(id) ;
            return cliente;
        }else{
            throw new ClienteAssociadoException( "Cliente Associado a pedido ou fatura" ) ;
        }

    }


    public Cliente recuperarPorId(Integer id) {
        Cliente cliente =  clienteDAO.recuperarPorId(id);
        if( cliente == null){
            throw new EntidadeNaoEncontradaException( "Cliente inexistente" ) ;
        }
        return cliente ;
    }


    public List<Cliente> recuperarTodoss(){
        return clienteDAO.recuperarTodos();
    }


    public Cliente alterarNome(Cliente cliente , String nome){
        cliente.setNome(nome);
        return cliente;
    }

    public Cliente alterarEmail(Cliente cliente , String email){
        cliente.setEmail(email);
        return cliente;
    }

    public Cliente alterarTelefone(Cliente cliente , String telefone) {
        cliente.setTelefone(telefone);
        return cliente;
    }

    public Cliente alterarCpf(Cliente cliente , String cpf) {
        cliente.setCpf(cpf);
        return cliente;
    }



    }
