package br.com.dev.esteroliver.pratiler.service.b_application.dto.usuario;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.PapelUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioPostDTO (

        @NotBlank(message = "E-mail é obrigatório.")
        String email,

        @NotBlank(message = "Senha é obrigatória.")
        String senha,

        @NotNull(message = "Papel do usuário é obrigatório.")
        PapelUsuario papelUsuario

){
}
