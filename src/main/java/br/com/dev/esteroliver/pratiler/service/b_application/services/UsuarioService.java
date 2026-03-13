package br.com.dev.esteroliver.pratiler.service.b_application.services;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.PapelUsuario;
import br.com.dev.esteroliver.pratiler.service.a_domain.model.Usuario;
import br.com.dev.esteroliver.pratiler.service.a_domain.repository.UsuarioRepository;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.JwtTokenDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.LoginUsuarioDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.usuario.UsuarioPostDTO;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.JwtTokenService;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.SecurityConfiguration;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SecurityConfiguration securityConfiguration;

    @Autowired
    JwtTokenService jwtTokenService;

    public void criarUsuario(UsuarioPostDTO usuarioPostDTO){
        if(usuarioRepository.findByEmail(usuarioPostDTO.email()).isPresent()) {
            throw new RuntimeException("E-mail indisponível.");
        }

        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioPostDTO.email());
        usuario.setSenha(securityConfiguration.passwordEncoder().encode(usuarioPostDTO.senha()));
        usuario.setPapel(usuarioPostDTO.papelUsuario());

        usuarioRepository.save(usuario);
    }

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
