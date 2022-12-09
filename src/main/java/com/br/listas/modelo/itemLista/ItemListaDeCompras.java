package com.br.listas.modelo.itemLista;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.br.listas.modelo.Produto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper=true)
@Setter
@Entity
public class ItemListaDeCompras extends AbstractItemLista{
	
	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto", nullable = true)
	private Produto produto;
	
	@Column(name = "quantidade", precision = 2, length = 5, scale = 2)
	private Double quantidade;

}
