package br.senac.menota.repositories;

import br.senac.menota.dtos.UpvoteGroupedByDate;
import br.senac.menota.model.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UpvoteRepository extends JpaRepository<Upvote, Long>, JpaSpecificationExecutor<Upvote> {

    Upvote findByUserIdAndProjetoId(String userId, Long projetoId);
    boolean existsByUserIdAndProjetoId(String userId, Long projetoId);

    @Query("SELECT new br.senac.menota.dtos.UpvoteGroupedByDate(u.dataCriacao, COUNT(u)) " +
            "FROM tb_upvote u WHERE u.projeto.id = :projetoId " +
            "GROUP BY u.dataCriacao ORDER BY u.dataCriacao DESC")
    List<UpvoteGroupedByDate> findAllDescByProjetoId(@Param("projetoId") Long projetoId);
}