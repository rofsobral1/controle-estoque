package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.MovimentacaoEstoqueDTO;
import com.gestao.estoque_backend.service.MovimentacaoEstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
@RequiredArgsConstructor
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    @PostMapping
    public ResponseEntity<MovimentacaoEstoqueDTO> criar(@RequestBody MovimentacaoEstoqueDTO dto) {
        return ResponseEntity.ok(movimentacaoEstoqueService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> listar() {
        return ResponseEntity.ok(movimentacaoEstoqueService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoqueDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoEstoqueService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        movimentacaoEstoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
