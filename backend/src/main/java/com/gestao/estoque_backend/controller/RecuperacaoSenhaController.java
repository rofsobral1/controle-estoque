package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.SenhaDTO;
import com.gestao.estoque_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recuperacao-senha")
@RequiredArgsConstructor
public class RecuperacaoSenhaController {

    private final UsuarioService usuarioService;

    // ===============================
    // VERIFICAR SE EMAIL EXISTE
    // ===============================
    @PostMapping("/verificar-email")
    public ResponseEntity<Void> verificarEmail(@RequestParam String email) {

        boolean existe = usuarioService.emailExiste(email);

        if (!existe) {
            return ResponseEntity.notFound().build(); // 404
        }

        return ResponseEntity.ok().build(); // 200
    }

    // ===============================
    // ATUALIZAR SENHA
    // ===============================
    @PostMapping("/atualizar-senha")
    public ResponseEntity<Void> atualizarSenha(@RequestBody SenhaDTO senhaDTO) {

        usuarioService.atualizarSenha(
                senhaDTO.getEmail(),
                senhaDTO.getNovaSenha()
        );

        return ResponseEntity.ok().build();
    }
}
