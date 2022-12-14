package com.br.listas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.listas.modelo.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
	
	
	@Query("FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
	public Usuario pesquisarValidarLoginSenha(@Param("email") String login, @Param("senha") String senha);

}
