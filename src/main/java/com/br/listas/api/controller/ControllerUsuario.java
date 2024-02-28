package com.br.listas.api.controller;

import com.br.listas.api.controller.dtoRequest.DtoAutenticarUsuarioRequest;
import com.br.listas.api.controller.dtoResponse.UsuarioResponseDTO;
import com.br.listas.api.controller.dtoResponse.converter.UsuarioConvertToResponse;
import com.br.listas.modelo.Usuario;
import com.br.listas.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/listas/usuarios")
public class ControllerUsuario {	
	
	@Autowired private RepositorioUsuario repositorio;
	
	@PostMapping()
	public ResponseEntity<?> gravarNovo(@RequestBody Usuario usuario){
		try {
			UsuarioResponseDTO resposta = UsuarioConvertToResponse.convert(repositorio.save(usuario));
			return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já cadastrado com o e-mail.");
		}
	}
	
	@PostMapping()
	@RequestMapping("/autenticar")
	public ResponseEntity<UsuarioResponseDTO> autenticar(@RequestBody DtoAutenticarUsuarioRequest dtoRequest){
		try {

			UsuarioResponseDTO resposta = UsuarioConvertToResponse.convert(
					repositorio.pesquisarValidarLoginSenha(dtoRequest.getLogin(), dtoRequest.getSenha()));

			return ResponseEntity.status(HttpStatus.OK).body(resposta);
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping
	@RequestMapping("/buscar-email/{email}")
	public ResponseEntity<?> buscarPorEmail(@PathVariable("email") String email) {
		Optional<Usuario> usuario = repositorio.findUsuarioByEmail(email);
		if(usuario.isPresent()) {
			UsuarioResponseDTO resposta = UsuarioConvertToResponse.convert(usuario.get());
			return ResponseEntity.ok(resposta);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para o e-mail informado."); 		
	}

}
