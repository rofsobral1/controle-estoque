package com.gestao.estoque_backend.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrevisaoEstoqueDTO {
    private Long id;
    private Long produtoId;
    private Integer quantidadePrevista;
    private LocalDateTime periodo;
    private LocalDateTime geradoEm;
}