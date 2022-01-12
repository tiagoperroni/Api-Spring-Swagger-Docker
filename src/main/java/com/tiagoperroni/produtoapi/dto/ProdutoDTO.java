package com.tiagoperroni.produtoapi.dto;

import lombok.Data;

@Data
public class ProdutoDTO {    

    private String nome;
    private Double preco;
    private Integer quantidade; 
    private Long categoria;

}
