package com.gestao.estoque_backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @Size(max = 18, message = "O CNPJ deve ter no máximo 18 caracteres")
    private String cnpj;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @Email(message = "E-mail inválido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String email;

    @Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres")
    private String endereco;

    @Size(max = 10, message = "O CEP deve ter no máximo 10 caracteres")
    private String cep;

    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    private String bairro;

    @Size(max = 50, message = "O estado deve ter no máximo 50 caracteres")
    private String estado;

    private LocalDateTime dataInclusao;
}
