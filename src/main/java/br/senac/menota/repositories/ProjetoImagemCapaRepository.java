package br.senac.menota.repositories;

import br.senac.menota.model.ProjetoImagemCapa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoImagemCapaRepository extends JpaRepository<ProjetoImagemCapa, Long> {

    ProjetoImagemCapa findByProjetoId(Long id);
}
