package br.senac.menota.resources;

import br.senac.menota.model.Entregavel;
import br.senac.menota.services.EntregavelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregavel")
@RequiredArgsConstructor
public class EntregavelController {

    private final EntregavelService entregavelService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Entregavel create(@RequestBody Entregavel entregavel){
        return entregavelService.create(entregavel);
    }
}
