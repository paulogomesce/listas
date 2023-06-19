package com.br.listas.api.controller.dtoRequest;

public enum EnumTipoLista {
	
	COMPRAS("Lista de compras", "ListaDeCompras"),
	DESEJOS("Lista de desejos", "ListaDeDesejos"),
	TAREFAS("Lista de tarefas", "ListaDeTarefas");

	private String nomeLista;
	private String dType;

	EnumTipoLista(String nomeLista, String dType){
		this.nomeLista = nomeLista;
		this.dType = dType;
	}

	public String getNomeLista(){
		return this.nomeLista;
	}
	public String getdType(){
		return dType;
	}

}
