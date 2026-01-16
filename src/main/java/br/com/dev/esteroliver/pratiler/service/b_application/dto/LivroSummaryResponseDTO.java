package br.com.dev.esteroliver.pratiler.service.b_application.dto;

import br.com.dev.esteroliver.pratiler.service.a_domain.model.Livro;

public record LivroSummaryResponseDTO(
    Long id,
    String titulo,
    Integer numPaginas,
    String autor
) {
    public static LivroSummaryResponseDTO fromEntity(Livro livro){
        return new LivroSummaryResponseDTO(
            livro.getId(), 
            livro.getTitulo(), 
            livro.getNumPaginas(), 
            livro.getAutor().getNome()
        );
    }
}
