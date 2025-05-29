package br.senac.menota.resources;

import br.senac.menota.model.Entregavel;
import br.senac.menota.services.EntregavelService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregavel")
@RequiredArgsConstructor
public class EntregavelController {

    private final EntregavelService entregavelService;

    @GetMapping("/list/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Entregavel> getByProjetctId(@PathVariable Long projectId){
        return entregavelService.getByProjetctId(projectId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Entregavel create(@RequestBody Entregavel entregavel){
        return entregavelService.create(entregavel);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entregavel update(@PathVariable Long id, @RequestBody Entregavel entregavel) {
        entregavel.setId(id);
        return entregavelService.create(entregavel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        entregavelService.delete(id);
    }
}
