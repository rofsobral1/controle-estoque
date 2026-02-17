package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.PrevisaoEstoqueDTO;
import com.gestao.estoque_backend.service.PrevisaoEstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/previsoes-estoque")
@RequiredArgsConstructor
public class PrevisaoEstoqueController {

    private final PrevisaoEstoqueService previsaoService;

    @PostMapping
    public ResponseEntity<PrevisaoEstoqueDTO> criar(@RequestBody PrevisaoEstoqueDTO dto) {
        return ResponseEntity.ok(previsaoService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PrevisaoEstoqueDTO>> listar() {
        return ResponseEntity.ok(previsaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrevisaoEstoqueDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(previsaoService.buscarPorId(id));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<PrevisaoEstoqueDTO>> buscarPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(previsaoService.buscarPorProduto(produtoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrevisaoEstoqueDTO> atualizar(
            @PathVariable Long id, 
            @RequestBody PrevisaoEstoqueDTO dto) {
        PrevisaoEstoqueDTO previsaoAtualizada = previsaoService.atualizar(id, dto);
        return ResponseEntity.ok(previsaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        previsaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}