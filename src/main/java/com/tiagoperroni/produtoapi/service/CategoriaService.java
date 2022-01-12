package com.tiagoperroni.produtoapi.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tiagoperroni.produtoapi.exception.ObjetoNaoEncontradoException;
import com.tiagoperroni.produtoapi.exception.ValidacaoDeCamposException;
import com.tiagoperroni.produtoapi.model.Categoria;
import com.tiagoperroni.produtoapi.repository.CategoriaRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) {
        this.validarCampos(categoria);
        categoria.setDataCadastro(LocalDateTime.now());
        return this.categoriaRepository.save(categoria);
    }

    public List<Categoria> listarTodos() {
        return this.categoriaRepository.findAll();
    }

    public Categoria buscarPorId(long id) {
        return this.categoriaRepository.findById(id)
        .orElseThrow(() ->  new ObjetoNaoEncontradoException(String.format("A categoria de id %s não foi encontrada", id)));
    }

    public Categoria atualizar(Long id, Categoria categoria) {
        this.validarCampos(categoria);
        var categoriaSalva = this.buscarPorId(id);
        BeanUtils.copyProperties(categoria, categoriaSalva, "id", "dataCadastro");
        return this.categoriaRepository.save(categoriaSalva);

    }

    public String deletar(Long id) {
        this.buscarPorId(id);
        this.categoriaRepository.deleteById(id);
        return String.format("Categoria de id %s deletada com sucesso.", id);
    }

    public void validarCampos(Categoria categoria) {
        if (categoria.getNome() == null) {
            throw new ValidacaoDeCamposException("O nome precisa ser preenchido");
        }
    }

}
