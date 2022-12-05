package com.br.listas.modelo.itemLista;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ItemListaDeDesejos extends AbstractItemLista{
	
	@Column(name = "nome_do_desejo", length = 100, nullable = false)
	private String nomeItem;

}
