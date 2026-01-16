package br.com.dev.esteroliver.pratiler.service.a_domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.dev.esteroliver.pratiler.service.b_application.dto.LivroPostDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "autor" , schema = "biblioteca")
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    @JsonIgnore
    private Set<Livro> livros;

    public Autor(LivroPostDTO dto){
        nome = dto.autor();
    }
}
