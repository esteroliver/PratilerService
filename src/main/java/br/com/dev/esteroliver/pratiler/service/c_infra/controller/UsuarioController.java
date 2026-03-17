package br.com.dev.esteroliver.pratiler.service.c_infra.controller;

import br.com.dev.esteroliver.pratiler.service.b_application.dto.leitor.LeitorPostDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastro/leitor")
    public ResponseEntity<Void> cadastrarUsuarioLeitor(@RequestBody LeitorPostDTO leitorPostDTO){
        usuarioService.cadastrarUsuarioLeitor(leitorPostDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
