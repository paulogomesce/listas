package com.br.listas.api.controller.dtoResponse.converter;

import com.br.listas.api.controller.dtoResponse.ItemListaResponseDTO;
import com.br.listas.api.controller.dtoResponse.ProdutoResponseDTO;
import com.br.listas.modelo.Produto;
import com.br.listas.modelo.itemLista.*;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ItemListaConvertToResponse {

    public static ItemListaResponseDTO convert(AbstractItemLista item){

        ProdutoResponseDTO produto = null;
        Double quantidade          = null;
        String nomeItem            = null;

        if(item instanceof ItemListaDeCompras) {
            produto    = ProdutoConverterToResponse.convert(((ItemListaDeCompras) item).getProduto());
            quantidade = ((ItemListaDeCompras)item).getQuantidade();
        }

        if(item instanceof ItemListaDeDesejos) {
            nomeItem = ((ItemListaDeDesejos)item).getNomeItem();
        }

        if(item instanceof ItemListaDeTarefas) {
            nomeItem   = ((ItemListaDeTarefas)item).getNomeItem();
        }

        return ItemListaResponseDTO.builder()
                .id(item.getId())
                .tipo(item.tipo)
                .status(item.getStatus())
                .produto(produto)
                .quantidade(quantidade)
                .nomeItem(nomeItem)
                .build();
    }

    public static List<ItemListaResponseDTO> convert(List<AbstractItemLista> itens){
        if(CollectionUtils.isEmpty(itens))
            return null;
        return itens.stream().map(item -> convert(item)).collect(Collectors.toList());
    }

}
