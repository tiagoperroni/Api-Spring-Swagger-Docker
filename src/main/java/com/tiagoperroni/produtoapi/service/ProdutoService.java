package com.tiagoperroni.produtoapi.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tiagoperroni.produtoapi.dto.ProdutoDTO;
import com.tiagoperroni.produtoapi.exception.ObjetoNaoEncontradoException;
import com.tiagoperroni.produtoapi.exception.ValidacaoDeCamposException;
import com.tiagoperroni.produtoapi.model.Produto;
import com.tiagoperroni.produtoapi.repository.ProdutoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Produto salvar(ProdutoDTO produtoDTO) {
        this.validarCampos(produtoDTO);
        var categoria = this.categoriaService.buscarPorId(produtoDTO.getCategoria());
        var produto = Produto.convert(produtoDTO);
        produto.setCategoria(categoria);
        produto.setDataCadastro(LocalDateTime.now());
        return this.produtoRepository.save(produto);

    }

    public List<Produto> listarTodos() {
        return this.produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return this.produtoRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException(
                        String.format("O produto de id %s não foi encontrado.", id)));
    }

    public List<Produto> buscarPorNome(String nome) {
        var produtos = this.produtoRepository.findByNomeIgnoreCaseContaining(nome);
        if ( produtos.size() == 0) {
            throw new ObjetoNaoEncontradoException("Não foram encontrados cadastros com o nome informado.");
        }
        return produtos; 
    }

    public Produto atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        this.validarCampos(produtoDTO);
        var produtoSalvo = this.buscarPorId(id);        
        var produto = Produto.convert(produtoDTO);
        produto.setCategoria(this.categoriaService.buscarPorId(produtoDTO.getCategoria()));        
        BeanUtils.copyProperties(produto, produtoSalvo, "id", "dataCadastro");      
        return this.produtoRepository.save(produtoSalvo);
    }

    public String deletar(Long id) {
        this.buscarPorId(id);
        this.produtoRepository.deleteById(id);
        return String.format("Produto de id %s deletado com sucesso.", id);
    }

    public void validarCampos(ProdutoDTO produto) {
        if (produto.getNome() == null) {
            throw new ValidacaoDeCamposException("O campo nome precisa ser preenchido.");
        }
        if (produto.getPreco() == null) {
            throw new ValidacaoDeCamposException("O campo preço precisa ser preenchido.");
        }
        if (produto.getQuantidade() == null) {
            throw new ValidacaoDeCamposException("O campo quantidade precisa ser preenchido.");
        }
        if (produto.getCategoria() == null) {
            throw new ValidacaoDeCamposException("O campo Categoria precisa ser preenchida.");
        }
    }

}
