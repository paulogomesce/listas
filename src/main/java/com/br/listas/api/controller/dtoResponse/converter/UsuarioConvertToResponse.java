package com.br.listas.api.controller.dtoResponse.converter;

import com.br.listas.api.controller.dtoResponse.UsuarioResponseDTO;
import com.br.listas.modelo.Usuario;

public class UsuarioConvertToResponse {

    public static UsuarioResponseDTO convert(Usuario usuario){
        if(usuario == null)
            return null;

        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}