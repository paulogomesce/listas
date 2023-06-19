package com.br.listas.api.controller;

import com.br.listas.api.controller.dtoRequest.DtoItemListaRequest;
import com.br.listas.modelo.Produto;
import com.br.listas.modelo.itemLista.*;
import com.br.listas.modelo.lista.AbstractLista;
import com.br.listas.modelo.lista.ListaDeCompras;
import com.br.listas.modelo.lista.ListaDeDesejos;
import com.br.listas.modelo.lista.ListaDeTarefas;
import com.br.listas.repositorio.RepositorioLista;
import com.br.listas.repositorio.RepositorioProduto;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/listas/itens")
public class ControllerItemLista implements ControllerAbstract<DtoItemListaRequest, AbstractItemLista>{

    @Autowired
    private RepositorioLista repositorioLista;
    @Autowired private RepositorioProduto repositorioProduto;

    //TODO: refatorar esse método para colocar a regra de negócio em um service
    @PostMapping
    public ResponseEntity<AbstractItemLista> adicionarNovo(@RequestBody DtoItemListaRequest request){
        try {
            Optional<AbstractLista> listaOptional = repositorioLista.findById(request.getIdLista());

            AbstractLista lista = listaOptional.orElseThrow(() -> new Exception("Lista não encontrada."));

            AbstractItemLista item = null;

            if(lista instanceof ListaDeCompras) {
                Produto produto = null;
                if(request.getProduto() == null) {
                    throw new Exception("Item de Lista de compra é necessário informar o produto.");
                }

                if(request.getProduto().getId() != null) {
                    produto = repositorioProduto.findById(request.getProduto().getId()).orElseThrow(() ->
                            new Exception("Produto não encontrado."));
                }else {
                    List<Produto> produtos = repositorioProduto.findByNameExato(request.getProduto().getNomeProduto());
                    if(produtos != null){
                        produto = produtos.iterator().next();
                    }
                    if(produto == null){
                        produto = new Produto();
                        produto.setNomeProduto(request.getProduto().getNomeProduto());
                        produto = repositorioProduto.save(produto);
                    }
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
            item.setStatus(request.getStatus());

            item = repositorioLista.gravarItem(item);

            return ResponseEntity.status(HttpStatus.CREATED).body(item);

        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<AbstractItemLista>> listarTodos() {
        return null;
    }

    @GetMapping
    @RequestMapping("/lista/{idLista}")
    public ResponseEntity<List<AbstractItemLista>> listarItensDaLista(@PathVariable long idLista){
        return ResponseEntity.ok(repositorioLista.listarItensDaLista(idLista));
    }

    @Override
    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody DtoItemListaRequest requestData) {
        try {
            AbstractItemLista item = repositorioLista.pesquisarItemPorId(requestData.getId());
            Produto produto = null;

            //TODO: consultar o produto somente se tiver mudado o produto
            if (requestData.getProduto().getId() != null) {
                produto = repositorioProduto.findById(requestData.getProduto().getId()).orElseThrow(() ->
                        new NoSuchElementException());
            }else {
                List<Produto> produtos = repositorioProduto.findByNameExato(requestData.getProduto().getNomeProduto());
                if (produtos != null && !produtos.isEmpty()){
                    produto = produtos.iterator().next();
                }
            }

            if(produto == null){
                produto = new Produto();
                produto.setNomeProduto(requestData.getProduto().getNomeProduto());
                produto = repositorioProduto.save(produto);
            }

            item.setProduto(produto);
            item.setNomeItem(requestData.getNomeItem());
            item.setQuantidade(requestData.getQuantidade());
            return ResponseEntity.ok(repositorioLista.atualizarItem(item));

        }catch(NoSuchElementException e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }catch(Exception e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar produto." + e.getMessage());
        }
    }

    @Override
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable long id) {
        AbstractItemLista item = repositorioLista.pesquisarItemPorId(id);
        if(item != null) {
            repositorioLista.deleteItem(item);
            return ResponseEntity.ok("Item deletado com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
    }

    @Override
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> pesquisarPorId(@PathVariable long id) {
        AbstractItemLista item = repositorioLista.pesquisarItemPorId(id);
        if(item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> alteraStatus(@RequestBody DtoItemListaRequest request){
        //TODO: melhorar desempenho
        AbstractItemLista item = repositorioLista.pesquisarItemPorId(request.getId());
        item.setStatus(request.getStatus());
        repositorioLista.atualizarItem(item);
        return ResponseEntity.ok(item);
    }

    @RequestMapping(path = "dominio-status", method = RequestMethod.GET)
    public ResponseEntity<EStatusItem[]> dominioStatus(){
        return ResponseEntity.ok(EStatusItem.values());
    }
}
