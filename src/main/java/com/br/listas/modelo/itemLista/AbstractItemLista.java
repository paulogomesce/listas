package com.br.listas.modelo.itemLista;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.br.listas.modelo.Produto;
import com.br.listas.modelo.lista.AbstractLista;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
	protected Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_lista", referencedColumnName = "id_lista")
	@JsonBackReference
	protected AbstractLista lista;
	
	@Column(name="dtype", insertable = false, updatable = false)
	public String tipo;

	@Column(name = "status")
	private EStatusItem status;
	
	@CreationTimestamp
	@Column(name="data_criacao", columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@UpdateTimestamp
	@Column(name="data_atualizacao", columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;
	
}
