package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.LogIaDTO;
import com.gestao.estoque_backend.service.LogIaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs-ia")
@RequiredArgsConstructor
public class LogIaController {

    private final LogIaService logIaService;

    @PostMapping
    public ResponseEntity<LogIaDTO> criar(@RequestBody LogIaDTO dto) {
        return ResponseEntity.ok(logIaService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<LogIaDTO>> listar() {
        return ResponseEntity.ok(logIaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogIaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(logIaService.buscarPorId(id));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<LogIaDTO>> buscarPorDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(logIaService.buscarPorDescricao(descricao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        logIaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}