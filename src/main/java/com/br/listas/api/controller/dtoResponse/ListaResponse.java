package com.br.listas.api.controller.dtoResponse;

import com.br.listas.modelo.Usuario;
import com.br.listas.modelo.itemLista.AbstractItemLista;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class ListaResponse {

    @EqualsAndHashCode.Include
    private long id;

    private String nomeLista;

    private String descricaoLista;

    private Usuario usuarioProprietario;

    private List<Usuario> usuariosConvidados;

    private List<AbstractItemLista> itens;

    private String tipo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private List<ItemListaResponse> itensListaResponse;

}
