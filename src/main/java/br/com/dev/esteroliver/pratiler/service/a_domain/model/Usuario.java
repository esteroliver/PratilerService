package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.PapelUsuario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "usuario" , name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PapelUsuario papel;
}
