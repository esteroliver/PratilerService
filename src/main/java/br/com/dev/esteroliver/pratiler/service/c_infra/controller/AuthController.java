package br.com.dev.esteroliver.pratiler.service.c_infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.JwtTokenDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.auth.LoginUsuarioDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController{

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> autenticarUsuario(@RequestBody LoginUsuarioDTO loginUsuarioDTO){
        JwtTokenDTO token = authService.autenticarUsuario(loginUsuarioDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}