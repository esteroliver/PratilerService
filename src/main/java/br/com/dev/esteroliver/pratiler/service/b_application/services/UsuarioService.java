package br.com.dev.esteroliver.pratiler.service.b_application.services;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.PapelUsuario;
import br.com.dev.esteroliver.pratiler.service.a_domain.model.Leitor;
import br.com.dev.esteroliver.pratiler.service.a_domain.model.Usuario;
import br.com.dev.esteroliver.pratiler.service.a_domain.repository.LeitorRepository;
import br.com.dev.esteroliver.pratiler.service.a_domain.repository.UsuarioRepository;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.leitor.LeitorPostDTO;
import br.com.dev.esteroliver.pratiler.service.c_infra.security.SecurityConfiguration;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    LeitorRepository leitorRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SecurityConfiguration securityConfiguration;

    @Transactional
    public void cadastrarUsuarioLeitor(LeitorPostDTO leitorPostDTO){
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

}
