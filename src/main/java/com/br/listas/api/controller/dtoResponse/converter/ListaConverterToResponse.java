package com.br.listas.api.controller.dtoResponse.converter;

import com.br.listas.api.controller.dtoRequest.EnumTipoLista;
import com.br.listas.api.controller.dtoResponse.ItemListaResponseDTO;
import com.br.listas.api.controller.dtoResponse.ListaResponseDTO;
import com.br.listas.api.controller.dtoResponse.UsuarioResponseDTO;
import com.br.listas.modelo.lista.AbstractLista;
import com.br.listas.modelo.lista.ListaDeCompras;
import com.br.listas.modelo.lista.ListaDeDesejos;
import com.br.listas.modelo.lista.ListaDeTarefas;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ListaConverterToResponse {

    public static ListaResponseDTO convert(AbstractLista lista, boolean adicionaItens) throws Exception {
        UsuarioResponseDTO usuario = UsuarioResponseDTO.builder()
                .id(lista.getUsuarioProprietario().getId())
                .nome(lista.getUsuarioProprietario().getNome())
                .build();

        List<ItemListaResponseDTO> itensLista = null;

        if(adicionaItens && !CollectionUtils.isEmpty(lista.getItens())){
            itensLista = ItemListaConvertToResponse.convert(lista.getItens());
        }

        return ListaResponseDTO.builder()
                .id(lista.getId())
                .nomeLista(lista.getNomeLista())
                .dataAtualizacao(lista.getDataAtualizacao())
                .descricaoLista(lista.getDescricaoLista())
                .dataCriacao(lista.getDataCriacao())
                .tipo(convertTipoLista(lista))
                .usuarioProprietario(usuario)
                .usuariosConvidados(null)
                .itensDaLista(itensLista)
                .build();
    }

    public static List<ListaResponseDTO> convert(List<AbstractLista> lista){
        List<ListaResponseDTO> listas = lista.stream().map(l -> {
            try {
                return convert(l, false);
            } catch (Exception e) {
                e.printStackTrace();
               return null;
            }
        }).collect(Collectors.toList());
        return listas;
    }

    public static EnumTipoLista convertTipoLista(AbstractLista lista) throws Exception {
        if(lista instanceof ListaDeCompras)
            return EnumTipoLista.COMPRAS;

        if(lista instanceof ListaDeDesejos)
            return EnumTipoLista.DESEJOS;

        if(lista instanceof ListaDeTarefas)
            return EnumTipoLista.TAREFAS;

        throw new Exception("Tipo de lista inválido");
    }



}
