package com.br.listas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.listas.modelo.lista.AbstractLista;

import java.util.List;

@Repository
public interface RepositorioLista extends JpaRepository<AbstractLista, Long>, RepositorioListaCustom{

    @Query("SELECT l FROM AbstractLista l WHERE l.tipo = :tipo")
    List<AbstractLista> findByDType(String tipo);
	
	
	
	
}
