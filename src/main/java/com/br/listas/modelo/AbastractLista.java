package com.br.listas.modelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

//TODO: estudar mapeamento de classes abstratas

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "LISTA")
public abstract class AbastractLista{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long id;
	
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_proprietario", referencedColumnName = "id")
	private Usuario usuarioProprietario;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="lista_usuarios_convidados",
	        	joinColumns = @JoinColumn(name="id_lista", referencedColumnName="id"),
	            inverseJoinColumns = @JoinColumn(name="id_usuario", referencedColumnName="id")
	    )
	private List<Usuario> usuariosConvidados;
	
	
}
