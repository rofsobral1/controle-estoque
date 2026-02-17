package com.gestao.estoque_backend.service;

import com.gestao.estoque_backend.dto.ProdutoDTO;
import com.gestao.estoque_backend.model.Produto;
import com.gestao.estoque_backend.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    
private final ProdutoRepository produtoRepository;

    public ProdutoDTO salvarProduto(ProdutoDTO dto) {
        Produto produto = Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .quantidade(dto.getQuantidade())
                .categoria(dto.getCategoria())
                .build();
        Produto salvo = produtoRepository.save(produto);
        dto.setId(salvo.getId());
        return dto;
    }

    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(p -> ProdutoDTO.builder()
                        .id(p.getId())
                        .nome(p.getNome())
                        .descricao(p.getDescricao())
                        .preco(p.getPreco())
                        .quantidade(p.getQuantidade())
                        .categoria(p.getCategoria())
                        .build())
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ProdutoDTO.builder()
                .id(p.getId())
                .nome(p.getNome())
                .descricao(p.getDescricao())
                .preco(p.getPreco())
                .quantidade(p.getQuantidade())
                .categoria(p.getCategoria())
                .build();
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
    
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setCategoria(dto.getCategoria());

        Produto atualizado = produtoRepository.save(produto);

        return ProdutoDTO.builder()
                .id(atualizado.getId())
                .nome(atualizado.getNome())
                .descricao(atualizado.getDescricao())
                .preco(atualizado.getPreco())
                .quantidade(atualizado.getQuantidade())
                .categoria(atualizado.getCategoria())
                .build();
    }

}
