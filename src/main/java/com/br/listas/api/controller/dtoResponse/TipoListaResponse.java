package com.br.listas.api.controller.dtoResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoListaResponse {

    private String id;
    private String nomeTipoLista;
    private String dType;

}
