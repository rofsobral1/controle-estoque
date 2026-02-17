package com.gestao.estoque_backend.service;

import com.gestao.estoque_backend.dto.LogIaDTO;
import com.gestao.estoque_backend.model.LogIa;
import com.gestao.estoque_backend.repository.LogIaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogIaService {

    private final LogIaRepository logIaRepository;

    public LogIaDTO salvar(LogIaDTO dto) {
        LogIa log = LogIa.builder()
                .descricao(dto.getDescricao())
                .detalhes(dto.getDetalhes())
                .geradoEm(LocalDateTime.now())
                .build();

        LogIa logSalvo = logIaRepository.save(log);
        return toDTO(logSalvo);
    }

    public List<LogIaDTO> listarTodos() {
        return logIaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LogIaDTO buscarPorId(Long id) {
        return logIaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Log não encontrado"));
    }

    public List<LogIaDTO> buscarPorDescricao(String descricao) {
        return logIaRepository.findByDescricaoContainingIgnoreCase(descricao).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        logIaRepository.deleteById(id);
    }

    private LogIaDTO toDTO(LogIa log) {
        return LogIaDTO.builder()
                .id(log.getId())
                .descricao(log.getDescricao())
                .detalhes(log.getDetalhes())
                .geradoEm(log.getGeradoEm())
                .build();
    }
}