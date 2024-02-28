package com.br.listas.repositorio;

import com.br.listas.api.controller.dtoResponse.ProdutoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.listas.modelo.Produto;

import java.util.List;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Long>, RepositorioProdutoCustom{

    @Query("FROM Produto p WHERE upper(p.nomeProduto) = upper(:nomeProduto)")
    List<Produto> findByNameExato(String nomeProduto);

    @Query("SELECT new com.br.listas.api.controller.dtoResponse.ProdutoResponseDTO(i.produto.id, i.produto.nomeProduto, COUNT(i.produto.id)) "
            + "FROM ItemListaDeCompras AS i "
            + "WHERE i.lista.usuarioProprietario.id = :idUsuario "
            + " GROUP BY i.produto.id, i.produto.nomeProduto "
            + "HAVING COUNT(i.produto.id) > 1 "
            + " ORDER BY COUNT(i.produto.id) DESC ")
    List<ProdutoResponseDTO> maisComprados(Long idUsuario);

}
