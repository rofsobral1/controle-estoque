package com.gestao.estoque_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fornecedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "O nome do fornecedor é obrigatório")
    private String nome;

    @Column(nullable = false, length = 20, unique = true)
    @NotBlank(message = "O CNPJ é obrigatório")
    private String cnpj;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    @Email(message = "E-mail inválido")
    private String email;

    @Column(length = 200)
    private String endereco;

    @Column(length = 10)
    private String cep;

    @Column(length = 100)
    private String bairro;

    @Column(length = 2)
    private String estado;

    @Column(name = "data_inclusao", nullable = false, updatable = false, insertable = false)
    private LocalDateTime dataInclusao;

}
