package br.com.dev.esteroliver.pratiler.service.a_domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="comentario", schema = "social")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pagina_final")
    private Integer paginaFinal;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    private String conteudo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Leitura leitura;
}
