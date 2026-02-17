package com.gestao.estoque_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestao.estoque_backend.dto.FornecedorDTO;
import com.gestao.estoque_backend.model.Fornecedor;
import com.gestao.estoque_backend.service.FornecedorService;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorDTO> criar(@RequestBody Fornecedor fornecedor) {
        return ResponseEntity.ok(fornecedorService.salvar(fornecedor));
    }

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> listar() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> atualizar(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        FornecedorDTO atualizado = fornecedorService.atualizar(id, fornecedor);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}