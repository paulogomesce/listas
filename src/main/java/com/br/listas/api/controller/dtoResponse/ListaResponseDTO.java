package com.br.listas.api.controller.dtoResponse;

import com.br.listas.api.controller.dtoRequest.EnumTipoLista;
import com.br.listas.modelo.Usuario;
import com.br.listas.modelo.itemLista.AbstractItemLista;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ListaResponseDTO {

    @EqualsAndHashCode.Include
    private long id;

    private String nomeLista;

    private String descricaoLista;

    private UsuarioResponseDTO usuarioProprietario;

    private List<UsuarioResponseDTO> usuariosConvidados;

    private EnumTipoLista tipo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

}
