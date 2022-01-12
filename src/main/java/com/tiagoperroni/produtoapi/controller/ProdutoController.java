package com.tiagoperroni.produtoapi.controller;

import java.util.List;

import com.tiagoperroni.produtoapi.dto.ProdutoDTO;
import com.tiagoperroni.produtoapi.model.Produto;
import com.tiagoperroni.produtoapi.service.ProdutoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @ApiOperation(value = "Listar Todos")
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return new ResponseEntity<>(this.produtoService.listarTodos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar por Id")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable long id) {
        return new ResponseEntity<>(this.produtoService.buscarPorId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar por Nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(this.produtoService.buscarPorNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Salvar Produto")
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody ProdutoDTO produto) {
        return new ResponseEntity<>(this.produtoService.salvar(produto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar Produto")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produto) {
        return new ResponseEntity<>(this.produtoService.atualizarProduto(id, produto), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Deletar Produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id) {
        return new ResponseEntity<>(this.produtoService.deletar(id), HttpStatus.ACCEPTED);
    }

}
