package com.br.listas.modelo.lista;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.br.listas.modelo.itemLista.ItemListaDeDesejos;

@Entity
public class ListaDeDesejos extends AbstractLista{
	
	@OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ItemListaDeDesejos> itensDaLista;

}
