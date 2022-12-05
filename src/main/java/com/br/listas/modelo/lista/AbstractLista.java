package com.br.listas.modelo.lista;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
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

import com.br.listas.modelo.Usuario;

import lombok.Data;
import lombok.EqualsAndHashCode;

//TODO: estudar mapeamento de classes abstratas

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "LISTA")
public abstract class AbstractLista{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_lista")
	private long id;
	
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_proprietario", referencedColumnName = "id_usuario")
	private Usuario usuarioProprietario;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="lista_usuarios_convidados",
	        	joinColumns = @JoinColumn(name="id_lista", referencedColumnName="id_lista"),
	            inverseJoinColumns = @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
	    )
	private List<Usuario> usuariosConvidados;
	
	
}
