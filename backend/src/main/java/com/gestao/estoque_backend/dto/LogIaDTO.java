package com.gestao.estoque_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LogIaDTO {
    private Long id;
    private String descricao;
    private String detalhes; // JSON como String
    private LocalDateTime geradoEm;
}