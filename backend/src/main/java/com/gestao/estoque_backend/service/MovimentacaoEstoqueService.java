package com.gestao.estoque_backend.service;

import com.gestao.estoque_backend.dto.MovimentacaoEstoqueDTO;
import com.gestao.estoque_backend.model.MovimentacaoEstoque;
import com.gestao.estoque_backend.model.Produto;
import com.gestao.estoque_backend.repository.MovimentacaoEstoqueRepository;
import com.gestao.estoque_backend.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoEstoqueDTO salvar(MovimentacaoEstoqueDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        MovimentacaoEstoque movimentacao = MovimentacaoEstoque.builder()
                .produto(produto)
                .tipo(dto.getTipo())
                .quantidade(dto.getQuantidade())
                .origem(dto.getOrigem())
                .build();

        return toDTO(movimentacaoEstoqueRepository.save(movimentacao));
    }

    public List<MovimentacaoEstoqueDTO> listarTodos() {
        return movimentacaoEstoqueRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MovimentacaoEstoqueDTO buscarPorId(Long id) {
        return movimentacaoEstoqueRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
    }

    public void deletar(Long id) {
        movimentacaoEstoqueRepository.deleteById(id);
    }

    private MovimentacaoEstoqueDTO toDTO(MovimentacaoEstoque movimentacao) {
        return MovimentacaoEstoqueDTO.builder()
                .id(movimentacao.getId())
                .produtoId(movimentacao.getProduto().getId())
                .tipo(movimentacao.getTipo())
                .quantidade(movimentacao.getQuantidade())
                .origem(movimentacao.getOrigem())
                .dataMovimentacao(movimentacao.getDataMovimentacao())
                .build();
    }
}
