package br.com.dev.esteroliver.pratiler.service.a_domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.esteroliver.pratiler.service.a_domain.model.Leitor;

@Repository
public interface LeitorRepository extends JpaRepository<Leitor, Long>{

}