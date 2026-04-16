package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.StatusLeitura;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "leitura" , schema = "social")
public class Leitura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_leitura", nullable = false)
    private StatusLeitura statusLeitura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leitor_id", nullable = false)
    private Leitor leitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;
}
