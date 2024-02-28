package com.br.listas.api.controller;

import com.br.listas.api.controller.dtoResponse.ProdutoResponseDTO;
import com.br.listas.repositorio.RepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas/produtos")
public class ControllerProduto {

    @Autowired
    private RepositorioProduto repositorioProduto;

    @GetMapping
    @RequestMapping("/mais-usados/{idUsuario}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarMaisUsados(@PathVariable Long idUsuario){
        repositorioProduto.listarMaisUsados(idUsuario);
        return null;
    }

}
