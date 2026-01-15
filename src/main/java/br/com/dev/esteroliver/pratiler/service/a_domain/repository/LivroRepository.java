package br.com.dev.esteroliver.pratiler.service.a_domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.esteroliver.pratiler.service.a_domain.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}