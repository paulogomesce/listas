package com.br.listas.repositorio;

import com.br.listas.modelo.itemLista.AbstractItemLista;

import java.util.List;

public interface RepositorioListaCustom {
	
	AbstractItemLista gravarItem(AbstractItemLista itemLista);
	List<AbstractItemLista> listarItensDaLista(long idLista);
	AbstractItemLista atualizarItem(AbstractItemLista item);
	AbstractItemLista pesquisarItemPorId(long idItem);
	void deleteItem(AbstractItemLista item);
}