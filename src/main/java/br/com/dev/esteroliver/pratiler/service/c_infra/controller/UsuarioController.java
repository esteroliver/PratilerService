package br.com.dev.esteroliver.pratiler.service.c_infra.controller;

import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.JwtTokenDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.LoginUsuarioDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.usuario.UsuarioPostDTO;
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

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> autenticarUsuario(@RequestBody LoginUsuarioDTO loginUsuarioDTO){
        JwtTokenDTO token = usuarioService.autenticarUsuario(loginUsuarioDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> criarUsuario(@RequestBody UsuarioPostDTO usuarioPostDTO){
        usuarioService.criarUsuario(usuarioPostDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Remover depois
    @GetMapping("/meus_dados")
    public ResponseEntity<String> meusDados(){
        return new ResponseEntity<>("Autenticado com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/teste_leitor")
    public ResponseEntity<String> testeLeitor(){
        return new ResponseEntity<>("Teste com leitor realizado com sucesso.", HttpStatus.OK);
    }

    @GetMapping("/teste_adm")
    public ResponseEntity<String> testeAdm(){
        return new ResponseEntity<>("Teste com administrador realizado com sucesso.", HttpStatus.OK);
    }
}
