package com.gestao.estoque_backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraDTO {

    private Long id;

    @NotNull(message = "O fornecedor é obrigatório")
    private Long fornecedorId;

    @NotNull(message = "O produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantidade;

    @NotNull(message = "O valor unitário é obrigatório")
    @DecimalMin(value = "0.01", inclusive = true, message = "O valor unitário deve ser maior que zero")
    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;

    private LocalDateTime dataCompra;

    @NotNull(message = "O usuário responsável é obrigatório")
    private Long usuarioId;

    private String observacao;

    private LocalDateTime dataRecebimento;

    private String status;
}
