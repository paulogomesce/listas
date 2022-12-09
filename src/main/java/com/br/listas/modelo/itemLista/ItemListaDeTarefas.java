package com.br.listas.modelo.itemLista;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class ItemListaDeTarefas extends AbstractItemLista{
	
	@Column(name = "nome_da_tarefa", length = 100, nullable = true)
	private String nomeItem;

}
