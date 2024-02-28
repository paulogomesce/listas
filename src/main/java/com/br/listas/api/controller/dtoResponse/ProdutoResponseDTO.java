package com.br.listas.api.controller.dtoResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoResponseDTO {

    public ProdutoResponseDTO(Long id, String nomeProduto, Long quantidadeCompras) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.quantidadeCompras = quantidadeCompras;
    }

    public ProdutoResponseDTO(){

    }

    @EqualsAndHashCode.Include
    private Long id;
    private String nomeProduto;
    private Long quantidadeCompras;

}
