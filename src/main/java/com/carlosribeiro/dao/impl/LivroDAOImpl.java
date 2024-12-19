package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.LivroDAO;
import com.carlosribeiro.model.Livro;

import java.util.List;

public class LivroDAOImpl extends DAOGenericoImpl<Livro> implements LivroDAO {
    public List<Livro> recuperarTodosOsLivrosOrdenados(int id) {
        return map.values().stream()
                .sorted((p1, p2) -> p1.getTitulo().compareTo(p2.getTitulo()))
                .toList();
    }
}
