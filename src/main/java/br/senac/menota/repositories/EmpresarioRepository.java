package br.senac.menota.repositories;

import br.senac.menota.model.Empresario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresarioRepository extends JpaRepository<Empresario, Long> {

    Optional<Empresario> findByCpf(String cpf);
    Optional<Empresario> findByEmail(String email);
    Optional<Empresario> findByNumeroCelular(String numeroCelular);
}
