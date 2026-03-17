package br.com.dev.esteroliver.pratiler.service.b_application.services;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.PapelUsuario;
import br.com.dev.esteroliver.pratiler.service.a_domain.model.Leitor;
import br.com.dev.esteroliver.pratiler.service.a_domain.model.Usuario;
import br.com.dev.esteroliver.pratiler.service.a_domain.repository.LeitorRepository;
import br.com.dev.esteroliver.pratiler.service.a_domain.repository.UsuarioRepository;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.JwtTokenDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.LoginUsuarioDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.leitor.LeitorPostDTO;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.JwtTokenService;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.SecurityConfiguration;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    LeitorRepository leitorRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SecurityConfiguration securityConfiguration;

    @Autowired
    JwtTokenService jwtTokenService;

    @Transactional
    public void criarUsuarioLeitor(LeitorPostDTO leitorPostDTO){
        if(usuarioRepository.findByEmail(leitorPostDTO.email()).isPresent()) {
            throw new RuntimeException("E-mail indisponível.");
        }

        Usuario usuario = new Usuario();

        usuario.setEmail(leitorPostDTO.email());
        usuario.setSenha(securityConfiguration.passwordEncoder().encode(leitorPostDTO.senha()));
        usuario.setPapel(PapelUsuario.LEITOR);

        usuario = usuarioRepository.save(usuario);

        Leitor leitor = new Leitor();

        leitor.setNome(leitorPostDTO.nome());
        leitor.setBiografia(leitorPostDTO.biografia());
        leitor.setUsuario(usuario);

        leitorRepository.save(leitor);
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
