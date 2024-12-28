package com.carlosribeiro;

import com.carlosribeiro.exception.ClienteAssociadoException;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.model.Cliente;
import com.carlosribeiro.service.ClienteService;
import corejava.Console;

import java.util.List;

public class PrincipalCliente {

    private final ClienteService clienteService = new ClienteService();
    Cliente cliente;
    String cpf;
    String nome;
    String telefone;
    String email;


    public void principal(){
        boolean continua = true;
        while(continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um Clinete");
            System.out.println("2. Alterar um Clinete");
            System.out.println("3. Remover um Clinete");
            System.out.println("4. Listar todos os Clientes");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            switch(opcao){

                case 1 ->{
                    cpf = Console.readLine("Digite CPF: ");
                    nome = Console.readLine("Digite Nome: ");
                    telefone = Console.readLine("Digite Telefone: ");
                    email = Console.readLine("Digite E-mail: ");
                    Cliente cliente = new Cliente(cpf , nome, telefone, email);
                    clienteService.incluir(cliente);
                    System.out.println("\nCliente número " + cliente.getId() + " cadastrado com sucesso!");
                }

                case 2 ->{
                    int id = Console.readInt("Digite Número do Cliente que deseja alterar: ") ;

                    try{
                        cliente  = clienteService.recuperarPorId(id);
                    }catch ( EntidadeNaoEncontradaException e ){
                        System.out.println( '\n' +  e.getMessage());
                        break;
                    }
                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1.Cpf");
                    System.out.println("2.Nome");
                    System.out.println("3.Email");
                    System.out.println("4.Telefone");


                    int opcaoAlteracao = Console.readInt("\nDigite Operacao: ") ;
                    switch(opcaoAlteracao){

                        case 1 ->{
                            cpf = Console.readLine("Digite novo CPF: ");
                            clienteService.alterarCpf(cliente , cpf) ;
                            System.out.println("\nCliente alterado com sucesso!\n");
                        }
                        case 2 ->{
                            nome = Console.readLine("Digite novo Nome: ");
                            clienteService.alterarNome(cliente , nome) ;
                            System.out.println("\nCliente alterado com sucesso!\n");
                        }
                        case 3 ->{
                            email = Console.readLine("Digite novo E-mail: ");
                            clienteService.alterarEmail(cliente , email) ;
                            System.out.println("\nCliente alterado com sucesso!\n");
                        }
                        case 4 ->{
                            telefone = Console.readLine("Digite novo telefone: ");
                            clienteService.alterarTelefone(cliente , telefone) ;
                            System.out.println("\nCliente alterado com sucesso!\n");

                        }
                        default -> System.out.println('\n' + "Opção inválida!");
                    }

                }

                case 3 ->{
                    int id = Console.readInt("Digite Número do Livro que deseja Remover : ") ;

                    try{
                        clienteService.remover(id);
                        System.out.println("\nCliente removido com sucesso!\n");
                    }catch ( ClienteAssociadoException e ){
                        System.out.println( '\n' +  e.getMessage());
                    }

                }


                case 4 ->{
                    List<Cliente> clinetes = clienteService.recuperarTodoss();
                    for (Cliente c : clinetes) {
                        System.out.println(c);
                    }
                }

                case 5 ->{
                    continua = false;
                }
                default ->{
                    System.out.println("Opção Inválida");
                }

            }

        }


    }


}
