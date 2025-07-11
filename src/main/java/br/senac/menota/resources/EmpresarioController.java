package br.senac.menota.resources;

import br.senac.menota.dtos.EmpresarioCreateRequestDTO;
import br.senac.menota.dtos.EmpresarioCreateResponseDTO;
import br.senac.menota.services.EmpresarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresario")
@RequiredArgsConstructor
public class EmpresarioController {

    private final EmpresarioService empresarioService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresarioCreateResponseDTO create(@RequestBody EmpresarioCreateRequestDTO empresario){
        return empresarioService.create(empresario);
    }
}
