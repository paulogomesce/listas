package com.br.listas.api.controller.dtoRequest;

import com.br.listas.modelo.Produto;

import lombok.Data;

@Data
public class DtoItemListaRequest {
	
	private Long idLista;
	private Produto produto;//TODO: criar DTO
	private String nomeItem;
	private Double quantidade;
	

}
