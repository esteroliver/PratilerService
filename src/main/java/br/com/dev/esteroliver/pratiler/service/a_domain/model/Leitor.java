package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "pessoa" , name = "leitor")
public class Leitor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    private String biografia;

    @OneToOne
    private Usuario usuario;

    //todo foto perfil
}
