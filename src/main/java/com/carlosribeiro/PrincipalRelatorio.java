package com.carlosribeiro;

import com.carlosribeiro.dao.FaturaDAO;
import com.carlosribeiro.dao.impl.FaturaDAOImpl;
import com.carlosribeiro.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.model.Fatura;
import com.carlosribeiro.model.ItemFaturado;
import com.carlosribeiro.model.Livro;
import com.carlosribeiro.service.FaturaService;
import com.carlosribeiro.service.LivroService;
import com.carlosribeiro.util.FabricaDeDaos;
import corejava.Console;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrincipalRelatorio {
    //apenasrecuperar

    public void principal(){
        FaturaService faturaService = new FaturaService() ;
        LivroService livroService = new LivroService() ;
        List<Fatura> faturas = faturaService.recuperarTodos() ;



        boolean continua = true ;
        while(continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Determinar Quantidade Faturada do livro em um mes ou ano");
            System.out.println("2. Listar Livros que nunca foram faturados");
            System.out.println("3. Listar livros que foram faturados em determinado mes e ano");
            System.out.println("4. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
            switch(opcao){

                case 1->{
                    Livro livro;
                    int idLivro = Console.readInt("Digite Número do Livro que deseja o Relatório: ") ;
                    try{
                        livro = livroService.recuperarPorId(idLivro);

                    }catch (EntidadeNaoEncontradaException e ){
                        System.out.println( '\n' +  e.getMessage());
                        break;
                    }


                    String dataAno = Console.readLine("Digte mes e ano que deseja analisar [MM/yyyy]  : ") ;
                    if( !validarMesAno(dataAno) ){
                        System.out.println("Data Inválida");
                        break ;
                    }

                    for( Fatura fatura : faturas ){

                        String s = fatura.getDataEmissaoMasc().substring(3) ;
                        if( !(s.equals(dataAno) ) ) continue;

                        List<ItemFaturado> itemFaturados = fatura.getItensFaturados() ;

                        for( ItemFaturado item : itemFaturados ){
                            if(  item.getItemPedido().getLivro().getId() == idLivro  ){
                                livro.addQtdFaturadoNoMes(item.getQtdFaturada()  );
                            }

                        }

                    }
                    if( livro.getQtdFaturadoNoMes() == 0  ){
                        System.out.println("\nLivro não foi faturado no mes selecionado");
                        break;
                    }

                    System.out.println("\nO livro " + livro.getTitulo() + " foi faturado " +  livro.getQtdFaturadoNoMes() + " vezes no mes selecionado" );
                    livro.setQtdFaturadoNoMes(0);
                }


                case 2 ->{

                    List<Livro> livros = livroService.recuperarTodos() ;
                    List<Livro> livrosFaturados  = new ArrayList<Livro>() ;
                    for( Fatura fatura : faturas ){
                        List<ItemFaturado> itensFaturados = fatura.getItensFaturados() ;
                        for( ItemFaturado item : itensFaturados ){
                            Livro livro = item.getItemPedido().getLivro();

                            livrosFaturados.add(livro);
                        }
                    }

                    Iterator<Livro> iterator = livros.iterator();
                    while (iterator.hasNext()) {
                        Livro livroTotal = iterator.next();
                        for (Livro livroFaturado : livrosFaturados) {
                            if (livroTotal.getId() == livroFaturado.getId()) {
                                iterator.remove();
                                break; 
                            }
                        }
                    }


                    System.out.println("Livros Nunca Faturados: ");
                    for(Livro livro : livros ){
                        System.out.println(livro.getTitulo());
                    }
                }


                case 3 ->{

                    String dataAno = Console.readLine("Digte mes e ano que deseja analisar [MM/yyyy]  : ") ;
                    if( !validarMesAno(dataAno) ){
                        System.out.println("Data Inválida");
                        break ;
                    }

                    List<Livro> livrosFaturados = new ArrayList<Livro>();
                    for( Fatura fatura : faturas ){

                        if( fatura.getDataCancelamentoMasc() != null ) continue; //fatura já foi cancelada .

                        String s = fatura.getDataEmissaoMasc().substring(3) ;

                        if( s.equals(dataAno) ){
                            List<ItemFaturado> itemFaturados = fatura.getItensFaturados() ;
                            for( ItemFaturado item : itemFaturados ){
                                Livro livro = item.getItemPedido().getLivro() ;
                                boolean v = verificarLivro(livro, livrosFaturados, item); //adiciona no atdfaturada no mes livro caso já esteja na lista ,
                                if( !v ){
                                    //se não estiver na lista adicionar quantidade faturada
                                    livro.addQtdFaturadoNoMes(item.getQtdFaturada());
                                    livrosFaturados.add(livro);
                                }
                            }

                        }

                    }
                    System.out.println("\nLivros Faturados no Mês selacionado: ");
                    for( Livro livro : livrosFaturados ){
                        System.out.println("Titulo: " + livro.getTitulo() + " | quantidade: " + livro.getQtdFaturadoNoMes() );
                        livro.setQtdFaturadoNoMes(0); //retornar qtdFaturado no mes para 0 , pois ele so serve para essa função .
                    }

                }

                case 4 ->{
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }

        }
    }


    private boolean verificarLivro(Livro livro ,  List<Livro> livrosFaturados , ItemFaturado item  ){
        for( Livro l : livrosFaturados ){
            if( l.getId() == livro.getId() ){
                l.addQtdFaturadoNoMes(item.getQtdFaturada());
                return true;
            }
        }
        return false;
    }

    private boolean validarMesAno(String dataAno) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth.parse(dataAno, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;

        }
    }



}
