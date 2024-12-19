package com.carlosribeiro.service;

import com.carlosribeiro.dao.ClienteDAO;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.model.Cliente;
import com.carlosribeiro.util.FabricaDeDaos;

public class ClienteService {

    ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);

    //todo adicionar regras de negócio
    public Cliente incluir(Cliente cliente) {
        clienteDAO.incluir(cliente);
        return cliente;
    }

    //todo regras de négocio
    public Cliente remover(int id ) {
        Cliente cliente = recuperarPorId(id) ;
        cliente = clienteDAO.remover(id) ;
        return cliente;
    }


    public Cliente recuperarPorId(Integer id) {
        Cliente cliente =  clienteDAO.recuperarPorId(id);
        if( cliente == null){
            throw new EntidadeNaoEncontradaException( "Cliente inexistente" ) ;
        }
        return cliente ;
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
