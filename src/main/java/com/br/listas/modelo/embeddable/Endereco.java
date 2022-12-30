package com.br.listas.modelo.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Endereco {

	@Column(name = "endereco_rua")
	private String rua;
	
	@Column(name = "endereco_numero")
	private String numero;
	
	@Column(name = "endereco_bairro")
	private String bairro;
	
	@Column(name = "endereco_cidade")
	private String cidade;
	
	@Column(name = "endereco_uf")
	private String uf;
}
