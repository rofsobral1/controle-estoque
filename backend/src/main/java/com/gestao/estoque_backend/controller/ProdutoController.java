package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.ProdutoDTO;
import com.gestao.estoque_backend.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@Validated @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(produtoService.salvarProduto(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @Validated @RequestBody ProdutoDTO dto) {
        ProdutoDTO produtoAtualizado = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }
    
}
