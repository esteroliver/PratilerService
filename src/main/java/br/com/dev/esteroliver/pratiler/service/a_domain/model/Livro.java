package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import br.com.dev.esteroliver.pratiler.service.b_application.dto.livro.LivroPostDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "livro" , schema = "biblioteca")
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(name="num_paginas", nullable = false)
    private Integer numPaginas;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String sinopse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Livro(LivroPostDTO dto){
        titulo = dto.titulo();
        numPaginas = dto.numPaginas();
        sinopse = dto.sinopse();
        isbn = dto.isbn();
    }
}
