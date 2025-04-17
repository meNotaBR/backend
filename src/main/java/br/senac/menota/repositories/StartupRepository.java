package br.senac.menota.repositories;

import br.senac.menota.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StartupRepository extends JpaRepository<Startup, Long> {

    Optional<Startup> findByCnpj(String cnpj);
    Optional<Startup> findByNomeFantasia(String nomeFantasia);
    Startup findByEmpresarioId(String empresarioId);
}