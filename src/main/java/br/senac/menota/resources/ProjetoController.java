package br.senac.menota.resources;

import br.senac.menota.model.Projeto;
import br.senac.menota.services.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projeto")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto create(@RequestBody Projeto projeto){
        return projetoService.create(projeto);
    }
}
