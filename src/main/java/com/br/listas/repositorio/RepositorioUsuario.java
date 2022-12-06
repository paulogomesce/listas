package com.br.listas.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.br.listas.modelo.Usuario;

@Repository
public class RepositorioUsuario {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Transactional
	public Usuario gravar(Usuario usuario) {
		return manager.merge(usuario);
	}

}
