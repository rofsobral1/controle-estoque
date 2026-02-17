package com.gestao.estoque_backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimentacaoEstoqueDTO {

    private Long id;

    @NotNull(message = "O produto é obrigatório")
    private Long produtoId;

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo; // ENTRADA ou SAIDA

    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidade;

    private String origem; // COMPRA, VENDA, AJUSTE, etc.

    private LocalDateTime dataMovimentacao;
}
