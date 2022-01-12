package com.tiagoperroni.produtoapi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiagoperroni.produtoapi.dto.ProdutoDTO;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
@Entity(name = "tb_produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCadastro;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public static Produto convert(ProdutoDTO produtoDTO) {
        var produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto, "id", "categoria");
        return produto;
    }
}
