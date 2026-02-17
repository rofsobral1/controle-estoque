package com.gestao.estoque_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.gestao.estoque_backend.dto.FornecedorDTO;
import com.gestao.estoque_backend.model.Fornecedor;
import com.gestao.estoque_backend.repository.FornecedorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorDTO salvar(Fornecedor fornecedor) {
        return toDTO(fornecedorRepository.save(fornecedor));
    }

    public List<FornecedorDTO> listarTodos() {
        return fornecedorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FornecedorDTO buscarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    public FornecedorDTO atualizar(Long id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedorExistente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        // Atualiza os campos
        fornecedorExistente.setNome(fornecedorAtualizado.getNome());
        fornecedorExistente.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedorExistente.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedorExistente.setEmail(fornecedorAtualizado.getEmail());
        fornecedorExistente.setEndereco(fornecedorAtualizado.getEndereco());
        fornecedorExistente.setCep(fornecedorAtualizado.getCep());
        fornecedorExistente.setBairro(fornecedorAtualizado.getBairro());
        fornecedorExistente.setEstado(fornecedorAtualizado.getEstado());

        // Salva e retorna atualizado
        return toDTO(fornecedorRepository.save(fornecedorExistente));
    }

    public void deletar(Long id) {
        fornecedorRepository.deleteById(id);
    }

    private FornecedorDTO toDTO(Fornecedor fornecedor) {
        return FornecedorDTO.builder()
                .id(fornecedor.getId())
                .nome(fornecedor.getNome())
                .cnpj(fornecedor.getCnpj())
                .telefone(fornecedor.getTelefone())
                .email(fornecedor.getEmail())
                .endereco(fornecedor.getEndereco())
                .cep(fornecedor.getCep())
                .bairro(fornecedor.getBairro())
                .estado(fornecedor.getEstado())
                .dataInclusao(fornecedor.getDataInclusao())
                .build();
    }

}
