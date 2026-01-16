package br.com.dev.esteroliver.pratiler.service.b_application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroPostDTO(
    @NotBlank(message = "Título é obrigatório")
    String titulo,

    @NotNull(message = "Número de páginas é obrigatório")
    Integer numPaginas,

    @NotBlank(message = "ISBN é obrigatório")
    String isbn,

    String sinopse,
    
    @NotBlank(message = "Nome do autor é obrigatório")
    String autor
) {}
