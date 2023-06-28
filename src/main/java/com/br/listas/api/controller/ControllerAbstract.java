package com.br.listas.api.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerAbstract<IDtoRequest, Entidade> {

    ResponseEntity<?> adicionarNovo(IDtoRequest request);

    ResponseEntity<List<Entidade>> listarTodos();

    ResponseEntity<?> atualizar(IDtoRequest requestData);

    ResponseEntity<?> deletar(long id);

    ResponseEntity<?> pesquisarPorId(long id);
}
