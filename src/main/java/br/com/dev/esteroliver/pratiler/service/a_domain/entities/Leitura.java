package br.com.dev.esteroliver.pratiler.service.a_domain.entities;

import br.com.dev.esteroliver.pratiler.service.a_domain.enums.StatusLeitura;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="leitura", schema = "social")
public class Leitura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_leitura")
    private StatusLeitura statusLeitura;

    @OneToOne(fetch = FetchType.LAZY)
    private Leitor leitor;

    @OneToOne(fetch = FetchType.LAZY)
    private Livro livro;
}
