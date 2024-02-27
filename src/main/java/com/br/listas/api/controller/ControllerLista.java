package com.br.listas.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.br.listas.api.controller.dtoRequest.EnumTipoLista;
import com.br.listas.api.controller.dtoResponse.ListaResponseDTO;
import com.br.listas.api.controller.dtoResponse.TipoListaResponseDTO;
import com.br.listas.api.controller.dtoResponse.converter.ListaConverterToResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.listas.api.controller.dtoRequest.DtoListaRequest;
import com.br.listas.factory.FactoryAbstractLista;
import com.br.listas.modelo.lista.AbstractLista;
import com.br.listas.repositorio.RepositorioLista;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/listas")
public class ControllerLista implements ControllerAbstract<DtoListaRequest, ListaResponseDTO>{

	@Autowired private RepositorioLista repositorio;

	@PostMapping
	public ResponseEntity<?> adicionarNovo(@RequestBody DtoListaRequest request){
		AbstractLista lista = FactoryAbstractLista.contruir(request);
		try {
			ListaResponseDTO listaDTO = ListaConverterToResponse.convert(repositorio.save(lista), true);
			return ResponseEntity.status(HttpStatus.CREATED).body(listaDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao adicionar a lista> " + e.getMessage());
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<List<ListaResponseDTO>> listarTodos(){

		List<ListaResponseDTO> listas = ListaConverterToResponse.convert(repositorio.findAll());

		return ResponseEntity.ok(listas);
	}

	@RequestMapping(path = "/tipo/{tipoLista}", method = RequestMethod.GET)
	public ResponseEntity<List<ListaResponseDTO>> listarPorTipo(@PathVariable String tipoLista){
		EnumTipoLista eTipo = EnumTipoLista.valueOf(tipoLista);

		List<ListaResponseDTO> listas = ListaConverterToResponse.convert(repositorio.findByDType(eTipo.getdType()));

		return ResponseEntity.ok(listas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> pesquisarPorId(@PathVariable long id){
		Optional<AbstractLista> lista = repositorio.findById(id);

		if(lista.isPresent()){
			ListaResponseDTO resposta = null;
			try {
				resposta = ListaConverterToResponse.convert(lista.get(), true);
				return ResponseEntity.ok(resposta);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista não encontrada.");
	}

	@GetMapping
	@RequestMapping("/tipos")
	public ResponseEntity<List<TipoListaResponseDTO>> listarTipos(){
		List<TipoListaResponseDTO> tiposLista = new ArrayList<>();

		for(EnumTipoLista tipo : EnumTipoLista.values()){

			tiposLista.add(TipoListaResponseDTO.builder()
					.id(tipo.name())
					.nomeTipoLista(tipo.getNomeLista())
					.dType(tipo.getdType())
					.build());
		}

		return ResponseEntity.ok(tiposLista);
	}

	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody DtoListaRequest requestData){
		try {
			AbstractLista lista = repositorio.findById(requestData.getId()).orElseThrow(
					() -> new Exception("Lista não encontrada"));

			lista.setNomeLista(requestData.getNomeLista());
			lista.setDescricaoLista(requestData.getDescricaoLista());

			return ResponseEntity.ok(ListaConverterToResponse.convert(repositorio.save(lista), true));

		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista não encontrada.");
		}
	}

	@DeleteMapping
	@RequestMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable long id){
		try {
			repositorio.deleteById(id);
			return ResponseEntity.ok("Lista deletada");
		}catch (EmptyResultDataAccessException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista não existe");
		}
	}

}
