package com.br.listas.repositorio.impl;

import com.br.listas.modelo.Produto;
import com.br.listas.repositorio.RepositorioProdutoCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RepositorioProdutoImpl implements RepositorioProdutoCustom {

    @PersistenceContext
    private EntityManager manager;

    //@Override
    //public List<Produto> listarMaisUsados(Long idUsuario) {

        /*SELECT p.id_produto, p.nome_produto, COUNT(1) quantidade
        FROM item_lista il, produto p
        WHERE il.id_produto = p.id_produto
        GROUP by p.id_produto, p.nome_produto
        ORDER BY 3 desc;*/


        //System.out.println("Pesquisado" + idUsuario);
        //return null;
    //}
}
