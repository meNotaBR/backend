package br.senac.menota.repositories;

import br.senac.menota.model.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpvoteRepository extends JpaRepository<Upvote, Long> {

    boolean existsByInvestidorIdAndProjetoId(Long investidorId, Long projetoId);
}