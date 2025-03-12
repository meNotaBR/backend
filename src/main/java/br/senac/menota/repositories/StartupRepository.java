package br.senac.menota.repositories;

import br.senac.menota.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<Startup, Long> {
}