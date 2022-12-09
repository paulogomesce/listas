package com.br.listas.repositorio;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.br.listas.modelo.itemLista.AbstractItemLista;
import com.br.listas.modelo.lista.AbstractLista;

@Repository
public class RepositorioLista extends RepositorioGenerico<AbstractLista, Long>{
	
	RepositorioLista(){
		super(AbstractLista.class);
	}
	
	@Transactional
	public AbstractItemLista gravarItem(AbstractItemLista itemLista) {
		return manager.merge(itemLista);
	}
	
	
}
