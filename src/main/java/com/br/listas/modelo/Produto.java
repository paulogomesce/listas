package com.br.listas.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "produto")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {
	
	@Id
	@Column(name = "id_produto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nomeProduto;
	
	@CreationTimestamp
	@Column(name="data_criacao", columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@UpdateTimestamp
	@Column(name="data_atualizacao", columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;

}
