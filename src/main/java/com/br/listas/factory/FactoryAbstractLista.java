package com.br.listas.factory;

import com.br.listas.api.controller.dtoRequest.DtoListaRequest;
import com.br.listas.api.controller.dtoRequest.EnumTipoLista;
import com.br.listas.modelo.Usuario;
import com.br.listas.modelo.lista.AbstractLista;
import com.br.listas.modelo.lista.ListaDeCompras;
import com.br.listas.modelo.lista.ListaDeDesejos;
import com.br.listas.modelo.lista.ListaDeTarefas;

public class FactoryAbstractLista {
	
	public static AbstractLista contruir(DtoListaRequest request) {
		
		AbstractLista lista = null;
		
		if(request.getTipoLista().equals(EnumTipoLista.COMPRAS)) {
			lista = new ListaDeCompras();
		}		
		if(request.getTipoLista().equals(EnumTipoLista.DESEJOS)) {
			lista = new ListaDeDesejos();					
		}		
		if(request.getTipoLista().equals(EnumTipoLista.TAREFAS)) {
			lista = new ListaDeTarefas();					
		}
		
		Usuario usuarioProprietario = new Usuario();
		usuarioProprietario.setId(request.getIdUsuarioProprietario());
		
		lista.setUsuarioProprietario(usuarioProprietario);
		lista.setNomeLista(request.getNomeLista());
		lista.setDescricaoLista(request.getDescricaoLista());
		
		return lista;
		
	}

}
