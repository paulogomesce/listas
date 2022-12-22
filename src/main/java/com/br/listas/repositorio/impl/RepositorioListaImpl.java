package com.br.listas.repositorio.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.br.listas.modelo.itemLista.AbstractItemLista;
import com.br.listas.repositorio.RepositorioListaCustom;

@Repository
public class RepositorioListaImpl implements RepositorioListaCustom {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public AbstractItemLista gravarItem(AbstractItemLista itemLista) {
		return manager.merge(itemLista);
	}

}
