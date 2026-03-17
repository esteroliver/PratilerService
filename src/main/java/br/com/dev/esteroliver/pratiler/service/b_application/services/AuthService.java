package br.com.dev.esteroliver.pratiler.service.b_application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.JwtTokenDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.LoginUsuarioDTO;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.JwtTokenService;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.UserDetailsImpl;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;    

    public JwtTokenDTO autenticarUsuario(LoginUsuarioDTO loginUsuarioDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUsuarioDTO.email(), loginUsuarioDTO.senha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        assert userDetails != null;

        return new JwtTokenDTO(
                jwtTokenService.gerarToken(userDetails),
                userDetails.getUsername(),
                userDetails.getUsuario().getPapel()
        );
    }
    
}
