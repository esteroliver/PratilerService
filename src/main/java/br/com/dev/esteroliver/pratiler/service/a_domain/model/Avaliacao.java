package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacao" , schema = "social")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer nota;

    private String conteudo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Leitura leitura;
}
