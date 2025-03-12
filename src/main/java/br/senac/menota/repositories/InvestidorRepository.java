package br.senac.menota.repositories;

import br.senac.menota.model.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestidorRepository extends JpaRepository<Investidor, Long> {
}