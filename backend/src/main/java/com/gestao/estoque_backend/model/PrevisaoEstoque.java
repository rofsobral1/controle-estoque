package com.gestao.estoque_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "previsoes_estoque")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrevisaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade_prevista", nullable = false)
    private Integer quantidadePrevista;

    @Column(name = "periodo", nullable = false)
    private LocalDateTime periodo;

    @Column(name = "gerado_em", nullable = false)
    private LocalDateTime geradoEm;
}