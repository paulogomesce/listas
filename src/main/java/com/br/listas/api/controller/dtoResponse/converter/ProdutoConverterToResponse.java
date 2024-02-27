package com.br.listas.api.controller.dtoResponse.converter;

import com.br.listas.api.controller.dtoResponse.ProdutoResponseDTO;
import com.br.listas.modelo.Produto;

public class ProdutoConverterToResponse {

    public static ProdutoResponseDTO convert(Produto produto){
        if(produto == null)
            return null;

        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .nomeProduto(produto.getNomeProduto())
                .build();
    }
}
