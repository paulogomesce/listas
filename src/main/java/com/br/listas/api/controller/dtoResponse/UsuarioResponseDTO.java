package com.br.listas.api.controller.dtoResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
}
