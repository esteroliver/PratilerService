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

    @Column(columnDefinition = "SMALLINT", nullable = false)
    private Integer nota;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String comentario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leitura_id", nullable = false)
    private Leitura leitura;
}
