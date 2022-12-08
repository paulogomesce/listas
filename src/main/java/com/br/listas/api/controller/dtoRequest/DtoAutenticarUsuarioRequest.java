package com.br.listas.api.controller.dtoRequest;

import lombok.Data;

@Data
public class DtoAutenticarUsuarioRequest {
	
	private String login;
	
	private String senha;

}
