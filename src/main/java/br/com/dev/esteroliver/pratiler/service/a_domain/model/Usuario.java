package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.PapelUsuario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "pessoa" , name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private PapelUsuario papel;
}
