package com.gestao.estoque_backend.service;

import com.gestao.estoque_backend.dto.UsuarioDTO;
import com.gestao.estoque_backend.model.Usuario;
import com.gestao.estoque_backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
/*
    private final UsuarioRepository usuarioRepository;

    // Salvar novo usuário
    public UsuarioDTO salvar(Usuario usuario) {
        return toDTO(usuarioRepository.save(usuario));
    }

    // Listar todos os usuários
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar usuário por ID
    public UsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Buscar usuário pelo email (necessário para autenticação JWT)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Atualizar usuário
    public UsuarioDTO atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());
        usuario.setPerfil(usuarioAtualizado.getPerfil());
        usuario.setAtivo(usuarioAtualizado.getAtivo());

        return toDTO(usuarioRepository.save(usuario));
    }

    // Deletar usuário
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Converter entidade para DTO
    public UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil())
                .ativo(usuario.getAtivo())
                .dataCriacao(usuario.getDataCriacao())
                .build();
    }
*/

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // SALVAR NOVO USUÁRIO
    public UsuarioDTO salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return toDTO(usuarioRepository.save(usuario));
    }

    // LISTAR TODOS
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public UsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // BUSCAR POR EMAIL
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // ATUALIZAR DADOS (SEM RESET DE SENHA)
    public UsuarioDTO atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setPerfil(usuarioAtualizado.getPerfil());
        usuario.setAtivo(usuarioAtualizado.getAtivo());

        return toDTO(usuarioRepository.save(usuario));
    }

    // RESET DE SENHA (ESQUECI MINHA SENHA)
    public void atualizarSenha(String email, String novaSenha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }

    // VERIFICAR SE EMAIL EXISTE
    public boolean emailExiste(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    // Deletar usuário
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // DTO
    public UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil())
                .ativo(usuario.getAtivo())
                .dataCriacao(usuario.getDataCriacao())
                .build();
    }

}
