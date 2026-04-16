package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "progresso" , schema = "social")
public class Progresso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "pagina_final", nullable = false)
    private Integer paginaFinal;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leitura_id", nullable = false)
    private Leitura leitura;
}
