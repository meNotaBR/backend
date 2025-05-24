package br.senac.menota.services;

import br.senac.menota.model.DashboardView;
import br.senac.menota.repositories.DashboardViewRepository;
import br.senac.menota.repositories.EntregavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardViewRepository dashboardViewRepository;
    private final EntregavelRepository entregavelRepository;
    private final UpvoteService upvoteService;

    public DashboardView getViewByProjetoId(Long id){
        DashboardView view = dashboardViewRepository.dashboardViewByProjectId(id);

        view.setUpvotes(upvoteService.findAllByProjetoId(id));
        view.setEntregaveis(entregavelRepository.findAllByProjetoIdOrderByDataCriacaoAsc(id));

        return view;
    }
}
