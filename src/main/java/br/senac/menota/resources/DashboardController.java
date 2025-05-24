package br.senac.menota.resources;

import br.senac.menota.model.DashboardView;
import br.senac.menota.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/view")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public DashboardView getView(@PathVariable("projectId")Long id){
        return dashboardService.getViewByProjetoId(id);
    }
}
