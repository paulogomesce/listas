package com.br.listas.modelo;

import com.br.listas.modelo.embeddable.Endereco;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Cacheable
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_usuario")
	private long id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 100, nullable = false, unique = true)
	private String email;//TODO: criar anotation para validar e-mails
	
	@Column(length = 32, nullable = false)
	private String senha;
	
	@Embedded
	private Endereco endereco;
	
	@CreationTimestamp
	@Column(name="data_criacao", columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@UpdateTimestamp
	@Column(name="data_atualizacao", columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;
}
