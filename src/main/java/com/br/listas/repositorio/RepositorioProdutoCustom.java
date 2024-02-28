package com.br.listas.repositorio;

import com.br.listas.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioProdutoCustom{

    List<Produto> listarMaisUsados(Long idUsuario);
}
