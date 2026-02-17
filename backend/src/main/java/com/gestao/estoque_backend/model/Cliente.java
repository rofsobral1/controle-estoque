package com.gestao.estoque_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 11, unique = true)
    private String cpf;

    @Column(length = 14, unique = true)
    private String cnpj;

    @Column(length = 255)
    private String endereco;

    @Column(length = 9)
    private String cep;

    @Column(length = 100)
    private String bairro;

    @Column(length = 2)
    private String estado;

    @Column(name = "data_inclusao", nullable = false, updatable = false, insertable = false)
    private LocalDateTime dataInclusao;  

}
