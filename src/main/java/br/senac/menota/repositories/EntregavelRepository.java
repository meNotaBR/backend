package br.senac.menota.repositories;

import br.senac.menota.model.Entregavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregavelRepository extends JpaRepository<Entregavel, Long> {

    List<Entregavel> findAllByProjetoIdOrderByDataCriacaoAsc(Long id);
}