package com.gestao.estoque_backend.service;

import org.springframework.stereotype.Service;
import com.gestao.estoque_backend.dto.ClienteDTO;
import com.gestao.estoque_backend.model.Cliente;
import com.gestao.estoque_backend.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteDTO salvarCliente(ClienteDTO dto) {
        Cliente cliente = mapToEntity(dto);
        Cliente salvo = clienteRepository.save(cliente);
        return mapToDTO(salvo);
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
        return mapToDTO(cliente);
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCpf(dto.getCpf());
        cliente.setCnpj(dto.getCnpj());
        cliente.setCep(dto.getCep());
        cliente.setBairro(dto.getBairro());
        cliente.setEstado(dto.getEstado());

        Cliente atualizado = clienteRepository.save(cliente);
        return mapToDTO(atualizado);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
        clienteRepository.delete(cliente);
    }

    // 🔹 Métodos utilitários de conversão
    private ClienteDTO mapToDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .endereco(cliente.getEndereco())
                .cpf(cliente.getCpf())
                .cnpj(cliente.getCnpj())
                .cep(cliente.getCep())
                .bairro(cliente.getBairro())
                .estado(cliente.getEstado())
                .dataInclusao(cliente.getDataInclusao())
                .build();
    }

    private Cliente mapToEntity(ClienteDTO dto) {
        return Cliente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .cpf(dto.getCpf())
                .cnpj(dto.getCnpj())
                .cep(dto.getCep())
                .bairro(dto.getBairro())
                .estado(dto.getEstado())
                .build();
    }
}
