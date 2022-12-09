package com.br.listas.repositorio;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.br.listas.modelo.Usuario;

@Repository
public class RepositorioUsuario extends RepositorioGenerico<Usuario, Long>{
	
	RepositorioUsuario(){
		super(Usuario.class);
	}
	
	public Usuario pesquisarValidarLoginSenha(String login, String senha) {
		String strQuery = "FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		TypedQuery<Usuario> query = manager.createQuery(strQuery, Usuario.class);
		query.setParameter("email", login);
		query.setParameter("senha", senha);
		return query.getSingleResult();		
	}

}
