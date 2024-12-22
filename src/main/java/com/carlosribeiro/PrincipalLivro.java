package com.carlosribeiro;


import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.model.Livro;
import com.carlosribeiro.service.LivroService;
import corejava.Console;

import java.util.List;

public class PrincipalLivro {

    private final LivroService livroService = new LivroService() ;

    String isbn;
    String titulo;
    String descricao;
    int qtdEstoque;
    double preco;

    Livro livro;


    public void principal(){
        boolean continua = true;
        while (continua){

            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um Livro");
            System.out.println("2. Alterar um Livro");
            System.out.println("3. Remover um Livro");
            System.out.println("4. Listar todos Livros");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            switch (opcao){
                case 1 -> {
                    isbn = Console.readLine("Informe o código do Livro: ");
                    titulo = Console.readLine("Informe o título do Livro: ");
                    descricao = Console.readLine("Informe a descrição do Livro: " );
                    qtdEstoque = Console.readInt("Informe a quantidade de estoque: ") ;

                    preco = Console.readDouble("Infome preço do Livro: ") ;

                    livro = new Livro(isbn , titulo , descricao , qtdEstoque ,preco) ;


                    livroService.incluir(livro);
                    System.out.println("\nLivro número " + livro.getId() + " cadastrado com sucesso!");
                }

                //case 2 e 3 fazer exceções
                case 2->{
                    int id = Console.readInt("Digite Número do Livro que deseja alterar: ") ;

                    try{
                        livro = livroService.recuperarPorId(id);
                    }catch ( EntidadeNaoEncontradaException e ){
                        System.out.println( '\n' +  e.getMessage());
                        break ;
                    }
                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1.Isbn");
                    System.out.println("2.Titulo");
                    System.out.println("3.Descrição");
                    System.out.println("4.Preço");
                    System.out.println("5.Quantidade");

                    int opcaoAlteracao = Console.readInt("\nDigite Operacao: ") ;

                    switch (opcaoAlteracao){
                        case 1 ->{
                            isbn = Console.readLine("Digite novo Isbn: ") ;
                            livroService.alterarIsbn(livro , isbn) ;
                            System.out.println("Isbn Alterado com sucesso!! \n");
                        }
                        case 2 ->{
                            titulo = Console.readLine("Digite Novo Titulo: ");
                            livroService.alterarTitulo( livro , titulo) ;
                            System.out.println("\nTitulo alterado com suceeso!! \n");
                        }
                        case 3 ->{
                            descricao = Console.readLine("Digite Nova Descrição: ");
                            livro.setDescricao(descricao);
                            livroService.alterarDescricao(livro , descricao) ;
                            System.out.println("\n Descrição Alterada com suceeso!! \n" );
                        }
                        case 4 ->{
                            preco = Console.readDouble("Digite novo preço: " ) ;
                            livro.setPreco(preco);
                            livroService.alterarPreco(livro , preco) ;

                            System.out.println("\n Preço Alterado Com suceeso!! \n");

                        }
                        case 5 ->{
                            qtdEstoque = Console.readInt("Quantidade de estoque: ");
                            livroService.alterarEstoque(livro , qtdEstoque) ;
                            System.out.println("Quantidade no estoque alterada com suceeso!! \n" );
                        }

                        default -> System.out.println('\n' + "Opção inválida!");
                    }


                }
                //todo : Remover apenas se não estiver sendo usado
                case 3 ->{

                    int id = Console.readInt("Digite Número do Livro que deseja Remover : ") ;
                    try {
                        livroService.remover(id);
                        System.out.println("\nLivro Removido com sucesso!!\n");
                    }catch (EntidadeNaoEncontradaException e ){
                        System.out.println('\n' + e.getMessage() );
                    }
                }

                case 4 ->{
                    List<Livro> l = livroService.recuperarTodos() ;
                    for(Livro elemento : l ){
                        System.out.println(elemento);
                    }

                }


                case 5->{
                    continua = false ;
                }
                default -> System.out.println('\n' + "Opção inválida!");
            }

        }

    }


}
