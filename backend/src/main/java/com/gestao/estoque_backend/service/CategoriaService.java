package com.gestao.estoque_backend.service;

import com.gestao.estoque_backend.dto.CategoriaDTO;
import com.gestao.estoque_backend.model.Categoria;
import com.gestao.estoque_backend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaDTO salvarCategoria(CategoriaDTO dto) {
        Categoria categoria = mapToEntity(dto);
        Categoria salva = categoriaRepository.save(categoria);
        return mapToDTO(salva);
    }

    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
        return mapToDTO(categoria);
    }

    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        Categoria atualizada = categoriaRepository.save(categoria);
        return mapToDTO(atualizada);
    }

    public void deletarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
        categoriaRepository.delete(categoria);
    }

    // 🔹 Métodos utilitários de conversão
    private CategoriaDTO mapToDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }

    private Categoria mapToEntity(CategoriaDTO dto) {
        return Categoria.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
    }
}
