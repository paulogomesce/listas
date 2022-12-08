package com.br.listas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.listas.api.controller.dtoRequest.DtoAutenticarUsuarioRequest;
import com.br.listas.modelo.Usuario;
import com.br.listas.repositorio.RepositorioUsuario;

@RestController
@RequestMapping("/usuarios")
public class ControllerUsuario {
	
	
	@Autowired private RepositorioUsuario repositorio;
	
	@PostMapping()
	public ResponseEntity<Usuario> gravarNovo(@RequestBody Usuario usuario){
		try {
			Usuario usuarioGravado = repositorio.gravar(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGravado);
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
	}
	
	@PostMapping()
	@RequestMapping("/autenticar")
	public ResponseEntity<Usuario> autenticar(@RequestBody DtoAutenticarUsuarioRequest dtoRequest){
		try {
			Usuario usuarioGravado = repositorio.pesquisarValidarLoginSenha(dtoRequest.getLogin(), dtoRequest.getSenha());
			return ResponseEntity.status(HttpStatus.OK).body(usuarioGravado);
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
