package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "usuario" , name = "leitor")
public class Leitor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String biografia;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private Usuario usuario;

    //todo foto perfil
}
