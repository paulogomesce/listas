package com.br.listas.api.controller.dtoRequest;

import com.br.listas.modelo.Produto;

import com.br.listas.modelo.itemLista.EStatusItem;
import lombok.Data;

@Data
public class DtoItemListaRequest  implements IDtoRequest{
	
	private long id;
	private Long idLista;
	private Produto produto;//TODO: criar DTO
	private String nomeItem;
	private Double quantidade;
	private EStatusItem status;
	

}
