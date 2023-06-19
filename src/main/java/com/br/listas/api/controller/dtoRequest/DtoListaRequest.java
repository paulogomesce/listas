package com.br.listas.api.controller.dtoRequest;

import java.util.List;

import lombok.Data;

@Data
public class DtoListaRequest  implements IDtoRequest{
	
	private long id;
	private Long idUsuarioProprietario;
	private List<Long> idUsuariosConvidados;	
	private EnumTipoLista tipoLista;
	private String nomeLista;
	private String descricaoLista;
	

}
