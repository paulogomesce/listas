package com.br.listas.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public abstract class RepositorioGenerico<Entidade, Id> {
	
	@PersistenceContext
	protected EntityManager manager;
	
	private Class<Entidade> entidade;
	
	protected RepositorioGenerico() {}

	protected RepositorioGenerico(Class<Entidade> persistedClass) {
		this();
		this.entidade = persistedClass;
	}	
	
	@Transactional
	public Entidade gravar(Entidade entidade) {
		return manager.merge(entidade);
	}
	
	public Entidade pesquisarPorId(Id id) {
		return manager.find(entidade, id);
	}
	
	public List<Entidade> listarTodos(){
		String jpql = "FROM " + entidade.getSimpleName() + " e";		
		TypedQuery<Entidade> query = manager.createQuery(jpql, entidade);
		return query.getResultList();
	}

}