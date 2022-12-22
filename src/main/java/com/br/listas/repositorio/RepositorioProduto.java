package com.br.listas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.listas.modelo.Produto;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Long>{

}
