package br.senac.menota.repositories;

import br.senac.menota.model.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpvoteRepository extends JpaRepository<Upvote, Long> {

    Upvote findByUserIdAndProjetoId(String userId, Long projetoId);
    boolean existsByUserIdAndProjetoId(String userId, Long projetoId);
}