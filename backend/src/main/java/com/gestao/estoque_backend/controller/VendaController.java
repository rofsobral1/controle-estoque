package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.VendaDTO;
import com.gestao.estoque_backend.service.VendaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaDTO> criarVenda(@RequestBody VendaDTO dto) {
        return ResponseEntity.ok(vendaService.salvarVenda(dto));
    }

    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarVendas() {
        return ResponseEntity.ok(vendaService.listarVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarVenda(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> atualizarVenda(@PathVariable Long id, @RequestBody VendaDTO dto) {
        return ResponseEntity.ok(vendaService.atualizarVenda(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }
}
