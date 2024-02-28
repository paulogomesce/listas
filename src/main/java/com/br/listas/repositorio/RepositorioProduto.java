package com.br.listas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.listas.modelo.Produto;

import java.util.List;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Long>, RepositorioProdutoCustom{

    @Query("FROM Produto p WHERE upper(p.nomeProduto) = upper(:nomeProduto)")
    List<Produto> findByNameExato(String nomeProduto);
}
