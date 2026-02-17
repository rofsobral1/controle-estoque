package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.CategoriaDTO;
import com.gestao.estoque_backend.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@Validated @RequestBody CategoriaDTO dto) {
        CategoriaDTO novaCategoria = categoriaService.salvarCategoria(dto);
        return ResponseEntity.ok(novaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoria(@PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id,
                                                           @Validated @RequestBody CategoriaDTO dto) {
        CategoriaDTO categoriaAtualizada = categoriaService.atualizarCategoria(id, dto);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
