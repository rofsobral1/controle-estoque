package com.gestao.estoque_backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaDTO {

    private Long id;

    @NotNull(message = "O produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "O cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private Integer quantidade;

    @NotNull(message = "O valor total é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero")
    private BigDecimal valorTotal;

    private LocalDateTime dataVenda;

    @NotBlank(message = "A forma de pagamento é obrigatória")
    @Size(max = 50, message = "A forma de pagamento deve ter no máximo 50 caracteres")
    private String formaPagamento;

    @Size(max = 2000, message = "A observação deve ter no máximo 2000 caracteres")
    private String observacao;
}
