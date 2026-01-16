package br.com.dev.esteroliver.pratiler.service.b_application.services;

import org.springframework.stereotype.Service;

import br.com.dev.esteroliver.pratiler.service.a_domain.model.Autor;
import br.com.dev.esteroliver.pratiler.service.a_domain.model.Livro;
import br.com.dev.esteroliver.pratiler.service.a_domain.repository.AutorRepository;
import br.com.dev.esteroliver.pratiler.service.a_domain.repository.LivroRepository;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.LivroPostDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.LivroResponseDTO;
import br.com.dev.esteroliver.pratiler.service.b_application.dto.LivroSummaryResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    public LivroResponseDTO criarLivro(LivroPostDTO dto) throws BadRequestException {
        if(livroRepository.findByIsbn(dto.isbn()).isPresent()){
            throw new BadRequestException("Já existe um livro com esse ISBN.");
        }
        
        if(dto.numPaginas() <= 0){
            throw new BadRequestException("Número de páginas deve ser maior que 0.");
        }
        
        Autor autor = new Autor(dto);

        if(autorRepository.findByNome(dto.autor()).isEmpty()){
            autorRepository.save(autor);
        }

        Livro livro = new Livro(dto);
        livro.setAutor(autor);

        return LivroResponseDTO.fromEntity(livroRepository.save(livro));
    }

    public LivroResponseDTO buscarPorId(Long id) throws BadRequestException {
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isEmpty())
            throw new BadRequestException("Não existe livro cadastrado com esse ID.");
        return LivroResponseDTO.fromEntity(livro.get());
    }

    public LivroResponseDTO buscarPorIsbn(String isbn) throws BadRequestException {
        Optional<Livro> livro = livroRepository.findByIsbn(isbn);
        if(livro.isEmpty())
            throw new BadRequestException("Não existe livro cadastrado com esse ISBN.");
        return LivroResponseDTO.fromEntity(livro.get());
    } 

    public List<LivroSummaryResponseDTO> listarLivros(){
        List<Livro> livros = livroRepository.findAll();

        return livros
                .stream()
                .map(LivroSummaryResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}