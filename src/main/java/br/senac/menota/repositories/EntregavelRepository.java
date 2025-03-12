package br.senac.menota.repositories;

import br.senac.menota.model.Entregavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregavelRepository extends JpaRepository<Entregavel, Long> {
}