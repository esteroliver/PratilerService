package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import br.com.dev.esteroliver.pratiler.service.b_application.dto.LivroPostDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "livro" , schema = "biblioteca")
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name="num_paginas")
    private Integer numPaginas;

    private String isbn;

    private String sinopse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Autor autor;

    public Livro(LivroPostDTO dto){
        titulo = dto.titulo();
        numPaginas = dto.numPaginas();
        sinopse = dto.sinopse();
    }
}
