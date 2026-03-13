package br.com.dev.esteroliver.pratiler.service.b_application.dto.usuario;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.PapelUsuario;

public record UsuarioResponseDTO (
        String email,
        PapelUsuario papelUsuario
) {
}
