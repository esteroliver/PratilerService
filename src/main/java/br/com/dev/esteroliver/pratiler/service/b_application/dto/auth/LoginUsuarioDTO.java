package br.com.dev.esteroliver.pratiler.service.b_application.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioDTO(

        @NotBlank(message = "E-mail é obrigatório.")
        String email,

        @NotBlank(message = "Senha é obrigatória.")
        String senha
) {
}
