package br.senac.menota.repositories;

import br.senac.menota.model.UpvoteCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpvoteCountRepository extends JpaRepository<UpvoteCount, Long> {

    UpvoteCount findByProjetoId(Long id);
}
