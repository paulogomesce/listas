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
    @RequestMapping("/mais-comprados/{idUsuario}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarMaisComprados(@PathVariable Long idUsuario){
        return ResponseEntity.ok(repositorioProduto.maisComprados(idUsuario));
    }

}
