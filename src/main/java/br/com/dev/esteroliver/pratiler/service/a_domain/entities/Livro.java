package br.com.dev.esteroliver.pratiler.service.a_domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "livro" , schema = "biblioteca")
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
}
