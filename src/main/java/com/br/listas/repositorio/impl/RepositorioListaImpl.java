package com.br.listas.repositorio.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.br.listas.modelo.itemLista.AbstractItemLista;
import com.br.listas.repositorio.RepositorioListaCustom;

import java.util.List;

@Repository
public class RepositorioListaImpl implements RepositorioListaCustom {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public AbstractItemLista gravarItem(AbstractItemLista itemLista) {
		return manager.merge(itemLista);
	}

	@Override
	public List<AbstractItemLista> listarItensDaLista(long idLista) {
		String jpql = "SELECT i from AbstractItemLista i WHERE i.lista.id = :idLista";
		TypedQuery<AbstractItemLista> query = manager.createQuery(jpql, AbstractItemLista.class);
		query.setParameter("idLista", idLista);
		return query.getResultList();
	}

	@Override
	@Transactional
	public AbstractItemLista atualizarItem(AbstractItemLista item){
		return manager.merge(item);
	}

	@Override
	public AbstractItemLista pesquisarItemPorId(long idItem){
		return manager.find(AbstractItemLista.class, idItem);
	}

	@Override
	@Transactional
	public void deleteItem(AbstractItemLista item){
		manager.remove(item);
	}

}
