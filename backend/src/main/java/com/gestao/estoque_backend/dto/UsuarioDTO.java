package com.gestao.estoque_backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 200, message = "A senha deve ter entre 6 e 200 caracteres")
    private String senha;

    @NotBlank(message = "O perfil é obrigatório")
    @Size(max = 50, message = "O perfil deve ter no máximo 50 caracteres")
    private String perfil;

    @NotNull(message = "O status de ativo é obrigatório")
    private Boolean ativo;

    private LocalDateTime dataCriacao;
}
