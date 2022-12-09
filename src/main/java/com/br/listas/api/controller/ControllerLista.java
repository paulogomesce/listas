package com.br.listas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/listas")
@CrossOrigin(origins = "*")
public class ControllerLista {
	
	@Autowired private RepositorioLista repositorio;
	@Autowired private RepositorioProduto repositorioProduto;
	
	@PostMapping
	public ResponseEntity<AbstractLista> adicionarNova(@RequestBody DtoListaRequest request){
		AbstractLista lista = FactoryAbstractLista.contruir(request);
		lista = repositorio.gravar(lista);
		return ResponseEntity.status(HttpStatus.CREATED).body(lista);
	}
	
	
	//TODO: refatorar esse método
	@PostMapping
	@RequestMapping("/adicionar-item")
	public ResponseEntity<AbstractItemLista> adicionarItem(@RequestBody DtoItemListaRequest request){
		try {
			AbstractLista lista = repositorio.pesquisarPorId(request.getIdLista());
			AbstractItemLista item = null;
			
			if(lista instanceof ListaDeCompras) {
				Produto produto = null;
				if(request.getProduto() == null) {
					throw new Exception("Item de Lista de compra é necessário informar o produto.");
				}
				
				if(request.getProduto().getId() != null) {
					produto = repositorioProduto.pesquisarPorId(request.getProduto().getId());
				}else {
					produto = new Produto();
					produto.setNomeProduto(request.getProduto().getNomeProduto());
					produto = repositorioProduto.gravar(produto);
				}			
				
				item = new ItemListaDeCompras();
				item.setProduto(produto);
				item.setQuantidade(request.getQuantidade());
			}
			
			if(lista instanceof ListaDeTarefas) {
				item = new ItemListaDeTarefas();
				item.setNomeItem(request.getNomeItem());
			}
	
			if(lista instanceof ListaDeDesejos) {
				item = new ItemListaDeDesejos();
				item.setNomeItem(request.getNomeItem());
			}		
			
			item.setLista(lista);
			
			item = repositorio.gravarItem(item);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(item);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<AbstractLista>> listarTodos(){
		return ResponseEntity.ok(repositorio.listarTodos());
	}
	
	//TODO: Criar serviço para listar os tipos de lista

}