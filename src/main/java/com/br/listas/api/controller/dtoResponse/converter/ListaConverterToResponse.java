package com.br.listas.api.controller.dtoResponse.converter;

import com.br.listas.api.controller.dtoRequest.EnumTipoLista;
import com.br.listas.api.controller.dtoResponse.ListaResponseDTO;
import com.br.listas.api.controller.dtoResponse.UsuarioResponseDTO;
import com.br.listas.modelo.lista.AbstractLista;

import java.util.List;
import java.util.stream.Collectors;

public class ListaConverterToResponse {

    public static ListaResponseDTO convert(AbstractLista lista) throws Exception {
        UsuarioResponseDTO usuario = UsuarioResponseDTO.builder()
                .id(lista.getUsuarioProprietario().getId())
                .nome(lista.getUsuarioProprietario().getNome())
                .build();

        return ListaResponseDTO.builder()
                .id(lista.getId())
                .nomeLista(lista.getNomeLista())
                .dataAtualizacao(lista.getDataAtualizacao())
                .descricaoLista(lista.getDescricaoLista())
                .dataCriacao(lista.getDataCriacao())
                .tipo(convertTipoLista(lista.getTipo()))
                .usuarioProprietario(usuario)
                .usuariosConvidados(null)
                .build();
    }

    public static List<ListaResponseDTO> convert(List<AbstractLista> lista){
        List<ListaResponseDTO> listas = lista.stream().map(l -> {
            try {
                return convert(l);
            } catch (Exception e) {
                e.printStackTrace();
               return null;
            }
        }).collect(Collectors.toList());
        return listas;
    }

    public static EnumTipoLista convertTipoLista(String tipo) throws Exception {
        if(tipo.equals(EnumTipoLista.COMPRAS.getdType()))
            return EnumTipoLista.COMPRAS;

        if(tipo.equals(EnumTipoLista.DESEJOS.getdType()))
            return EnumTipoLista.DESEJOS;

        if(tipo.equals(EnumTipoLista.TAREFAS.getdType()))
            return EnumTipoLista.TAREFAS;

        throw new Exception("Tipo de lista inválido");
    }



}
