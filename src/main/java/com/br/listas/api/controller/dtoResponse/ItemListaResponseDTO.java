package com.br.listas.api.controller.dtoResponse;

import com.br.listas.modelo.Produto;
import com.br.listas.modelo.itemLista.EStatusItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemListaResponseDTO {

    @EqualsAndHashCode.Include
    private Long id;
    private String tipo;
    private EStatusItem status;
    private ProdutoResponseDTO produto;
    private Double quantidade;
    private String nomeItem;

}
