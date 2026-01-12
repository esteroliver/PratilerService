package br.com.dev.esteroliver.pratiler.service.a_domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "pessoa" , name = "leitor")
public class Leitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String biografia;

    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    //todo foto perfil
}
