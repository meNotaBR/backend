package br.senac.menota.repositories;

import br.senac.menota.model.DashboardView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardViewRepository extends JpaRepository<DashboardView, Long> {

    @Query(value = "SELECT * FROM vw_advanced_project_dashboard WHERE project_id = :projectId", nativeQuery = true)
    DashboardView dashboardViewByProjectId(@Param("projectId") Long id);
}
