package com.br.listas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.listas.modelo.lista.AbstractLista;

@Repository
public interface RepositorioLista extends JpaRepository<AbstractLista, Long>, RepositorioListaCustom{
	
	
	
	
}
