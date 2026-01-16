package br.com.dev.esteroliver.pratiler.service.b_application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dev.esteroliver.pratiler.service.a_domain.repository.ComentarioRepository;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;
}
