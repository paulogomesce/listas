package com.br.listas.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.listas.api.controller.dtoRequest.DtoAutenticarUsuarioRequest;
import com.br.listas.modelo.Usuario;
import com.br.listas.repositorio.RepositorioUsuario;

@RestController
@RequestMapping("/listas/usuarios")
public class ControllerUsuario {	
	
	@Autowired private RepositorioUsuario repositorio;
	
	@PostMapping()
	public ResponseEntity<?> gravarNovo(@RequestBody Usuario usuario){
		try {
			Usuario usuarioGravado = repositorio.save(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGravado);
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já cadastrado com o e-mail.");
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
	
	@GetMapping
	@RequestMapping("/buscar-email/{email}")
	public ResponseEntity<?> buscarPorEmail(@PathVariable("email") String email) {
		Optional<Usuario> usuario = repositorio.findUsuarioByEmail(email);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para o e-mail informado."); 		
	}

}
