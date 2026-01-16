package br.com.dev.esteroliver.pratiler.service.a_domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.esteroliver.pratiler.service.a_domain.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
    Optional<Autor> findByNome(String nome);
}