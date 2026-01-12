package br.com.dev.esteroliver.pratiler.service.a_domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "pessoa" , name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;
}
