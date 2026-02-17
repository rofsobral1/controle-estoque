package com.gestao.estoque_backend.service;

import com.gestao.estoque_backend.dto.CompraDTO;
import com.gestao.estoque_backend.model.*;
import com.gestao.estoque_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final UsuarioRepository usuarioRepository;

    public CompraDTO salvar(CompraDTO dto) {
        // Recupera as entidades associadas
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Monta entidade Compra
        Compra compra = Compra.builder()
                .fornecedor(fornecedor)
                .produto(produto)
                .quantidade(dto.getQuantidade())
                .valorUnitario(dto.getValorUnitario())
                .valorTotal(dto.getValorUnitario()
                        .multiply(BigDecimal.valueOf(dto.getQuantidade())))
                .dataCompra(dto.getDataCompra() != null ? dto.getDataCompra() : LocalDateTime.now())
                .usuario(usuario)
                .observacao(dto.getObservacao())
                .dataRecebimento(dto.getDataRecebimento())
                .status(dto.getStatus() != null ? dto.getStatus() : "RECEBIDO")
                .build();

        // Salva compra
        Compra compraSalva = compraRepository.save(compra);

        // Registra movimentação de estoque
        MovimentacaoEstoque movimentacao = MovimentacaoEstoque.builder()
                .produto(produto)
                .tipo("ENTRADA")
                .quantidade(dto.getQuantidade())
                .origem("COMPRA")
                .fornecedor(fornecedor)
                .usuario(usuario)
                .cliente(null) // não se aplica em compra
                .build();

        movimentacaoEstoqueRepository.save(movimentacao);

        return toDTO(compraSalva);
    }

    public List<CompraDTO> listarTodos() {
        return compraRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CompraDTO buscarPorId(Long id) {
        return compraRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    public CompraDTO atualizar(Long id, CompraDTO dto) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        compra.setFornecedor(fornecedor);
        compra.setProduto(produto);
        compra.setQuantidade(dto.getQuantidade());
        compra.setValorUnitario(dto.getValorUnitario());
        compra.setValorTotal(dto.getValorUnitario().multiply(BigDecimal.valueOf(dto.getQuantidade())));
        compra.setUsuario(usuario);
        compra.setObservacao(dto.getObservacao());
        compra.setDataRecebimento(dto.getDataRecebimento());
        compra.setStatus(dto.getStatus());

        Compra compraAtualizada = compraRepository.save(compra);

        return toDTO(compraAtualizada);
    }

    public void deletar(Long id) {
        compraRepository.deleteById(id);
    }

    private CompraDTO toDTO(Compra compra) {
        return CompraDTO.builder()
                .id(compra.getId())
                .fornecedorId(compra.getFornecedor().getId())
                .produtoId(compra.getProduto().getId())
                .quantidade(compra.getQuantidade())
                .valorUnitario(compra.getValorUnitario())
                .valorTotal(compra.getValorTotal())
                .dataCompra(compra.getDataCompra())
                .usuarioId(compra.getUsuario().getId())
                .observacao(compra.getObservacao())
                .dataRecebimento(compra.getDataRecebimento())
                .status(compra.getStatus())
                .build();
    }
}
