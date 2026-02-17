package com.gestao.estoque_backend.service;

import com.gestao.estoque_backend.dto.PrevisaoEstoqueDTO;
import com.gestao.estoque_backend.model.PrevisaoEstoque;
import com.gestao.estoque_backend.model.Produto;
import com.gestao.estoque_backend.repository.PrevisaoEstoqueRepository;
import com.gestao.estoque_backend.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrevisaoEstoqueService {

    private final PrevisaoEstoqueRepository previsaoRepository;
    private final ProdutoRepository produtoRepository;

    public PrevisaoEstoqueDTO salvar(PrevisaoEstoqueDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        PrevisaoEstoque previsao = PrevisaoEstoque.builder()
                .produto(produto)
                .quantidadePrevista(dto.getQuantidadePrevista())
                .periodo(dto.getPeriodo())
                .geradoEm(LocalDateTime.now())
                .build();

        PrevisaoEstoque salva = previsaoRepository.save(previsao);
        return toDTO(salva);
    }

    public List<PrevisaoEstoqueDTO> listarTodos() {
        return previsaoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PrevisaoEstoqueDTO buscarPorId(Long id) {
        return previsaoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Previsão não encontrada"));
    }

    public List<PrevisaoEstoqueDTO> buscarPorProduto(Long produtoId) {
        return previsaoRepository.findByProdutoId(produtoId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PrevisaoEstoqueDTO atualizar(Long id, PrevisaoEstoqueDTO dto) {
        PrevisaoEstoque previsao = previsaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Previsão de estoque não encontrada"));

        // Atualiza os campos
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        previsao.setProduto(produto);
        previsao.setQuantidadePrevista(dto.getQuantidadePrevista());
        previsao.setPeriodo(dto.getPeriodo());

        PrevisaoEstoque previsaoSalva = previsaoRepository.save(previsao);
        return toDTO(previsaoSalva);
    }


    public void deletar(Long id) {
        previsaoRepository.deleteById(id);
    }

    private PrevisaoEstoqueDTO toDTO(PrevisaoEstoque previsao) {
        return PrevisaoEstoqueDTO.builder()
                .id(previsao.getId())
                .produtoId(previsao.getProduto().getId())
                .quantidadePrevista(previsao.getQuantidadePrevista())
                .periodo(previsao.getPeriodo())
                .geradoEm(previsao.getGeradoEm())
                .build();
    }
}