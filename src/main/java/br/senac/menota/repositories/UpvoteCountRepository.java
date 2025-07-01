package br.senac.menota.repositories;

import br.senac.menota.model.UpvoteCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UpvoteCountRepository extends JpaRepository<UpvoteCount, Long> {

    UpvoteCount findByProjetoId(Long id);

    @Query(nativeQuery = true, value = "select * from tb_upvote_count order by total_upvotes DESC limit 5")
    List<UpvoteCount> getProjectsByUpvoteCount();
}
