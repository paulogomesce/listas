package com.br.listas.repositorio;

import org.springframework.stereotype.Repository;

import com.br.listas.modelo.Produto;

@Repository
public class RepositorioProduto extends RepositorioGenerico<Produto, Long>{
	
	RepositorioProduto(){
		super(Produto.class);
	}

}
