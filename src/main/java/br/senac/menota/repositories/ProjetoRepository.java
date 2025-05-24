package br.senac.menota.repositories;

import br.senac.menota.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>, JpaSpecificationExecutor<Projeto> {

    List<Projeto> findAllByStartupId(Long startupId);
    Projeto findByUpvoteId(Long id);
}