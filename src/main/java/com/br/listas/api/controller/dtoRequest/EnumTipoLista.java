package com.br.listas.api.controller.dtoRequest;

public enum EnumTipoLista {
	
	COMPRAS("Lista de compras"),
	DESEJOS("Lista de desejos"),
	TAREFAS("Lista de tarefas");

	private String nomeLista;

	EnumTipoLista(String nomeLista){
		this.nomeLista = nomeLista;
	}

	public String getNomeLista(){
		return this.nomeLista;
	}

}
