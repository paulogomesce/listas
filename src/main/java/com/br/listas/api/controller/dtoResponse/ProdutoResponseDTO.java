package com.br.listas.api.controller.dtoResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoResponseDTO {

    @EqualsAndHashCode.Include
    private Long id;
    private String nomeProduto;
    //private LocalDateTime dataCriacao;
    //private LocalDateTime dataAtualizacao;

}
