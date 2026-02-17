package com.gestao.estoque_backend.service;

import com.gestao.estoque_backend.dto.VendaDTO;
import com.gestao.estoque_backend.model.Venda;
import com.gestao.estoque_backend.model.Produto;
import com.gestao.estoque_backend.model.Cliente;
import com.gestao.estoque_backend.model.Usuario;
import com.gestao.estoque_backend.repository.VendaRepository;
import com.gestao.estoque_backend.repository.ProdutoRepository;
import com.gestao.estoque_backend.repository.ClienteRepository;
import com.gestao.estoque_backend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public VendaDTO salvarVenda(VendaDTO dto) {
        Venda venda = toEntity(dto);
        if (venda.getDataVenda() == null) {
            venda.setDataVenda(LocalDateTime.now());
        }
        return toDTO(vendaRepository.save(venda));
    }

    public List<VendaDTO> listarVendas() {
        return vendaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VendaDTO buscarPorId(Long id) {
        return vendaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    public VendaDTO atualizarVenda(Long id, VendaDTO dto) {
        Venda vendaExistente = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        vendaExistente.setProduto(produto);
        vendaExistente.setCliente(cliente);
        vendaExistente.setUsuario(usuario);
        vendaExistente.setQuantidade(dto.getQuantidade());
        vendaExistente.setValorTotal(dto.getValorTotal());
        vendaExistente.setDataVenda(dto.getDataVenda() != null ? dto.getDataVenda() : vendaExistente.getDataVenda());
        vendaExistente.setFormaPagamento(dto.getFormaPagamento());
        vendaExistente.setObservacao(dto.getObservacao());

        return toDTO(vendaRepository.save(vendaExistente));
    }

    public void deletarVenda(Long id) {
        vendaRepository.deleteById(id);
    }

    private VendaDTO toDTO(Venda venda) {
        return VendaDTO.builder()
                .id(venda.getId())
                .produtoId(venda.getProduto().getId())
                .clienteId(venda.getCliente().getId())
                .usuarioId(venda.getUsuario().getId())
                .quantidade(venda.getQuantidade())
                .valorTotal(venda.getValorTotal())
                .dataVenda(venda.getDataVenda())
                .formaPagamento(venda.getFormaPagamento())
                .observacao(venda.getObservacao())
                .build();
    }

    private Venda toEntity(VendaDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return Venda.builder()
                .produto(produto)
                .cliente(cliente)
                .usuario(usuario)
                .quantidade(dto.getQuantidade())
                .valorTotal(dto.getValorTotal())
                .dataVenda(dto.getDataVenda())
                .formaPagamento(dto.getFormaPagamento())
                .observacao(dto.getObservacao())
                .build();
    }
}