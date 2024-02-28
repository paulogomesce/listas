package com.br.listas.api.controller;

import com.br.listas.api.controller.dtoRequest.DtoItemListaRequest;
import com.br.listas.api.controller.dtoResponse.ItemListaResponseDTO;
import com.br.listas.api.controller.dtoResponse.converter.ItemListaConvertToResponse;
import com.br.listas.modelo.Produto;
import com.br.listas.modelo.itemLista.*;
import com.br.listas.modelo.lista.AbstractLista;
import com.br.listas.modelo.lista.ListaDeCompras;
import com.br.listas.modelo.lista.ListaDeDesejos;
import com.br.listas.modelo.lista.ListaDeTarefas;
import com.br.listas.repositorio.RepositorioLista;
import com.br.listas.repositorio.RepositorioProduto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/listas/itens")
@Slf4j
public class ControllerItemLista implements ControllerAbstract<DtoItemListaRequest, AbstractItemLista>{

    @Autowired
    private RepositorioLista repositorioLista;
    @Autowired private RepositorioProduto repositorioProduto;

    //TODO: refatorar esse método para colocar a regra de negócio em um service
    @PostMapping
    public ResponseEntity<?> adicionarNovo(@RequestBody DtoItemListaRequest request){
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
                    if(produtos != null && !produtos.isEmpty()){
                        produto = produtos.iterator().next();
                    }
                    if(produto == null){
                        produto = new Produto();
                        produto.setNomeProduto(request.getProduto().getNomeProduto());
                        produto = repositorioProduto.save(produto);
                    }
                }

                item = new ItemListaDeCompras();
                ((ItemListaDeCompras)item).setProduto(produto);
                ((ItemListaDeCompras)item).setQuantidade(request.getQuantidade());
            }

            if(lista instanceof ListaDeTarefas) {
                item = new ItemListaDeTarefas();
                ((ItemListaDeTarefas)item).setNomeItem(request.getNomeItem());
            }

            if(lista instanceof ListaDeDesejos) {
                item = new ItemListaDeDesejos();
                ((ItemListaDeDesejos)item).setNomeItem(request.getNomeItem());
            }

            item.setLista(lista);
            item.setStatus(request.getStatus());

            item = repositorioLista.gravarItem(item);

            ItemListaResponseDTO itemResponse = ItemListaConvertToResponse.convert(item);

            return ResponseEntity.status(HttpStatus.CREATED).body(itemResponse);

        }catch(Exception e) {
            log.error("Erro ao gravar o item", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao gravar o item. " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<AbstractItemLista>> listarTodos() {
        return null;
    }

    @GetMapping
    @RequestMapping("/lista/{idLista}")
    public ResponseEntity<List<ItemListaResponseDTO>> listarItensDaLista(@PathVariable long idLista){
        List<ItemListaResponseDTO> resposta =
                ItemListaConvertToResponse.convert(repositorioLista.listarItensDaLista(idLista));

        return ResponseEntity.ok(resposta);
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

            if(item instanceof ItemListaDeCompras){
                ((ItemListaDeCompras)item).setProduto(produto);
                ((ItemListaDeCompras)item).setQuantidade(requestData.getQuantidade());
            }

            if(item instanceof ItemListaDeDesejos) {
                ((ItemListaDeDesejos)item).setNomeItem(requestData.getNomeItem());
            }

            if(item instanceof ItemListaDeTarefas) {
                ((ItemListaDeTarefas)item).setNomeItem(requestData.getNomeItem());
            }

            ItemListaResponseDTO resposta = ItemListaConvertToResponse.convert(repositorioLista.atualizarItem(item));

            return ResponseEntity.ok(resposta);

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
            ItemListaResponseDTO resposta = ItemListaConvertToResponse.convert(item);
            return ResponseEntity.ok(resposta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> alteraStatus(@RequestBody DtoItemListaRequest request){
        //TODO: melhorar desempenho
        AbstractItemLista item = repositorioLista.pesquisarItemPorId(request.getId());
        if(item != null) {
            item.setStatus(request.getStatus());
            repositorioLista.atualizarItem(item);
            ItemListaResponseDTO resposta = ItemListaConvertToResponse.convert(item);
            return ResponseEntity.ok(resposta);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
        }
    }

    @RequestMapping(path = "dominio-status", method = RequestMethod.GET)
    public ResponseEntity<EStatusItem[]> dominioStatus(){
        return ResponseEntity.ok(EStatusItem.values());
    }
}
