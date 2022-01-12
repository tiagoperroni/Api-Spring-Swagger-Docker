package com.tiagoperroni.produtoapi.controller;

import java.util.List;

import com.tiagoperroni.produtoapi.model.Categoria;
import com.tiagoperroni.produtoapi.service.CategoriaService;

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

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @ApiOperation(value = "Listar Todos")
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodos() {
        return new ResponseEntity<>(this.categoriaService.listarTodos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Listar por Código")
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable long id) {
        return new ResponseEntity<>(this.categoriaService.buscarPorId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Salvar Categoria")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(this.categoriaService.salvar(categoria), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar Categoria")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        return new ResponseEntity<>(this.categoriaService.atualizar(id, categoria), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Deletar Categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return new ResponseEntity<>(this.categoriaService.deletar(id), HttpStatus.ACCEPTED);
    }
}
