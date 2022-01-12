package com.tiagoperroni.produtoapi.repository;

import java.util.List;

import com.tiagoperroni.produtoapi.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
    List<Categoria> findByNomeIgnoreCaseContaining(String nome);
}
