package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.AuthRequest;
import com.gestao.estoque_backend.dto.AuthResponse;
import com.gestao.estoque_backend.dto.UsuarioDTO;
import com.gestao.estoque_backend.model.Usuario;
import com.gestao.estoque_backend.service.UsuarioService;
import com.gestao.estoque_backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        UsuarioDTO usuarioSalvo = usuarioService.salvar(usuario);
        String token = jwtService.generateToken(usuario);
        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .usuario(usuarioSalvo)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        Usuario usuario = usuarioService.buscarPorEmail(request.getEmail());
        String token = jwtService.generateToken(usuario);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .usuario(usuarioService.toDTO(usuario))
                .build());
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> me(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return ResponseEntity.ok(usuarioService.toDTO(usuario));
    }
}
