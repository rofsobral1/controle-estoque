package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.CompraDTO;
import com.gestao.estoque_backend.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraDTO> criar(@RequestBody CompraDTO compraDTO) {
        return ResponseEntity.ok(compraService.salvar(compraDTO));
    }

    @GetMapping
    public ResponseEntity<List<CompraDTO>> listar() {
        return ResponseEntity.ok(compraService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraDTO> atualizar(@PathVariable Long id, @RequestBody CompraDTO compraDTO) {
        return ResponseEntity.ok(compraService.atualizar(id, compraDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        compraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
