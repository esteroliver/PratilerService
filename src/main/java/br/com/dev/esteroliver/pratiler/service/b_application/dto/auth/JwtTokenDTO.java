package br.com.dev.esteroliver.pratiler.service.b_application.dto.auth;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.PapelUsuario;

public record JwtTokenDTO(
        String token,
        String email,
        PapelUsuario papelUsuario
) {
}
