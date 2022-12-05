package com.br.listas.modelo.itemLista;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.br.listas.modelo.Produto;

@Entity
public class ItemListaDeCompras extends AbstractItemLista{
	
	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	private Produto produto;

}
