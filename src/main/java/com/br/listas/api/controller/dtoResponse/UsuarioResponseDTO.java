package com.br.listas.api.controller.dtoResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
}
