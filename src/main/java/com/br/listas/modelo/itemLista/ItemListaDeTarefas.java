package com.br.listas.modelo.itemLista;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ItemListaDeTarefas extends AbstractItemLista{
	
	@Column(name = "nome_da_tarefa", length = 100, nullable = false)
	private String nomeItem;

}
