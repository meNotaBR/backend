package br.senac.menota.repositories;

import br.senac.menota.model.Empresario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresarioRepository extends JpaRepository<Empresario, String> {

    Optional<Empresario> findByCpf(String cpf);
    Empresario findByEmail(String email);
    Optional<Empresario> findByNumeroCelular(String numeroCelular);
}
