package br.com.dev.esteroliver.pratiler.service.b_application.dto;

import br.com.dev.esteroliver.pratiler.service.a_domain.model.Livro;

public record LivroResponseDTO(
    Long id,
    String titulo,
    String isbn,
    String sinopse,
    Integer numPaginas,
    String autor
) {
    public static LivroResponseDTO fromEntity(Livro livro){
        return new LivroResponseDTO(
            livro.getId(), 
            livro.getTitulo(), 
            livro.getIsbn(), 
            livro.getSinopse(), 
            livro.getNumPaginas(), 
            livro.getAutor().getNome()
        );
    }
}
