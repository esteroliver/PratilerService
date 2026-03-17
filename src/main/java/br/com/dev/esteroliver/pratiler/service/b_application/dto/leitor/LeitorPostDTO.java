package br.com.dev.esteroliver.pratiler.service.b_application.dto.leitor;

import jakarta.validation.constraints.NotBlank;

public record LeitorPostDTO(

        @NotBlank(message = "Nome do leitor é obrigatório.")
        String nome,

        String biografia,

        @NotBlank(message = "E-mail do leitor é obrigatório.")
        String email,

        @NotBlank(message = "Senha do leitor é obrigatória.")
        String senha
) {
}
