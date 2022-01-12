package com.tiagoperroni.produtoapi.repository;

import java.util.List;

import com.tiagoperroni.produtoapi.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeIgnoreCaseContaining(String nome);
    
}
