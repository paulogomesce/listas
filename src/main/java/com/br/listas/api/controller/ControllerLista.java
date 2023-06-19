package com.br.listas.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.br.listas.api.controller.dtoRequest.EnumTipoLista;
import com.br.listas.api.controller.dtoResponse.TipoListaResponse;
import com.br.listas.modelo.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.listas.api.controller.dtoRequest.DtoItemListaRequest;
import com.br.listas.api.controller.dtoRequest.DtoListaRequest;
import com.br.listas.factory.FactoryAbstractLista;
import com.br.listas.modelo.Produto;
import com.br.listas.modelo.itemLista.AbstractItemLista;
import com.br.listas.modelo.itemLista.ItemListaDeCompras;
import com.br.listas.modelo.itemLista.ItemListaDeDesejos;
import com.br.listas.modelo.itemLista.ItemListaDeTarefas;
import com.br.listas.modelo.lista.AbstractLista;
import com.br.listas.modelo.lista.ListaDeCompras;
import com.br.listas.modelo.lista.ListaDeDesejos;
import com.br.listas.modelo.lista.ListaDeTarefas;
import com.br.listas.repositorio.RepositorioLista;
import com.br.listas.repositorio.RepositorioProduto;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/listas")
public class ControllerLista implements ControllerAbstract<DtoListaRequest, AbstractLista>{

	@Autowired private RepositorioLista repositorio;

	@PostMapping
	public ResponseEntity<AbstractLista> adicionarNovo(@RequestBody DtoListaRequest request){
		AbstractLista lista = FactoryAbstractLista.contruir(request);
		lista = repositorio.save(lista);
		return ResponseEntity.status(HttpStatus.CREATED).body(lista);
	}

	@GetMapping
	public ResponseEntity<List<AbstractLista>> listarTodos(){
		return ResponseEntity.ok(repositorio.findAll());
	}

	@RequestMapping(path = "/tipo/{tipoLista}", method = RequestMethod.GET)
	public ResponseEntity<List<AbstractLista>> listarPorTipo(@PathVariable String tipoLista){
		EnumTipoLista eTipo = EnumTipoLista.valueOf(tipoLista);
		return ResponseEntity.ok(repositorio.findByDType(eTipo.getdType()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> pesquisarPorId(@PathVariable long id){
		Optional<AbstractLista> lista = repositorio.findById(id);
		if(lista.isPresent()){
			return ResponseEntity.ok(lista.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista não encontrada.");
	}

	@GetMapping
	@RequestMapping("/tipos")
	public ResponseEntity<List<TipoListaResponse>> listarTipos(){
		List<TipoListaResponse> tiposLista = new ArrayList<>();

		for(EnumTipoLista tipo : EnumTipoLista.values()){

			tiposLista.add(TipoListaResponse.builder()
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

			return ResponseEntity.ok(repositorio.save(lista));

		}catch(Exception e){
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
