package com.br.listas.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
	
	public Usuario pesquisarValidarLoginSenha(String login, String senha) {
		String strQuery = "FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		TypedQuery<Usuario> query = manager.createQuery(strQuery, Usuario.class);
		query.setParameter("email", login);
		query.setParameter("senha", senha);
		return query.getSingleResult();		
	}

}
