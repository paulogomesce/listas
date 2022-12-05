package com.br.listas.modelo.itemLista;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.br.listas.modelo.lista.AbstractLista;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "item_lista")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractItemLista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_item")
	protected long id;
	
	@ManyToOne
	@JoinColumn(name = "id_lista", referencedColumnName = "id_lista")
	protected AbstractLista lista;
	
	
}
