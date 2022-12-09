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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.br.listas.modelo.Usuario;
import com.br.listas.modelo.itemLista.AbstractItemLista;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@Column(name = "nome_lista", length = 70, nullable = false)
	private String nomeLista;
	
	@Column(name = "desc_lista", length = 150, nullable = true)
	private String descricaoLista;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_proprietario", referencedColumnName = "id_usuario")
	private Usuario usuarioProprietario;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="lista_usuarios_convidados",
	        	joinColumns = @JoinColumn(name="id_lista", referencedColumnName="id_lista"),
	            inverseJoinColumns = @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
	    )
	private List<Usuario> usuariosConvidados;
	
	@OneToMany(mappedBy = "lista", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<AbstractItemLista> itens;
	
	@Column(name="dtype", insertable = false, updatable = false)
	private String tipo;
	
	
}
