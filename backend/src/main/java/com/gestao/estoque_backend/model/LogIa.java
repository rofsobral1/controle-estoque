package com.gestao.estoque_backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs_ia")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogIa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String detalhes; // ou Map<String, Object> se quiser

    private LocalDateTime geradoEm;
}
