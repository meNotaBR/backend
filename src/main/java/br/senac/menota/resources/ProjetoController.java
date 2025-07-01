package br.senac.menota.resources;

import br.senac.menota.dtos.ProjetoCarrosselResponseDTO;
import br.senac.menota.dtos.ProjetoCreateRequestDTO;
import br.senac.menota.dtos.ProjetoFeedResponseDTO;
import br.senac.menota.model.DashboardView;
import br.senac.menota.model.Projeto;
import br.senac.menota.services.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projeto")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto create(@RequestBody ProjetoCreateRequestDTO projeto){
        return projetoService.create(projeto);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Projeto update(@PathVariable("id") Long id, @RequestBody ProjetoCreateRequestDTO projeto){
        return projetoService.update(id, projeto);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoFeedResponseDTO> getAll() throws InterruptedException {
        return projetoService.getAll();
    }

    @GetMapping("/carrossel")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoCarrosselResponseDTO> getCarrossel() throws InterruptedException {
        return projetoService.getProjetosCarrossel();
    }

    @GetMapping("/recentes")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoFeedResponseDTO> getUltimosProjetos() throws InterruptedException {
        return projetoService.getUltimosProjetos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Projeto getById(@PathVariable("id") Long id) throws InterruptedException {
        return projetoService.getById(id);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoFeedResponseDTO> getProjetosUsuarios(){
        return projetoService.getProjetosUsuario();
    }

    @GetMapping("/by-startup/{startupId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoFeedResponseDTO> getProjetosByStartupId(@PathVariable("startupId") Long startupId) {
        return projetoService.getProjetosByStartupId(startupId);
    }

    @GetMapping("/curtidos")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoFeedResponseDTO> getProjetosCurtidos(){
        return projetoService.getProjetosCurtidos();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        projetoService.delete(id);
    }
}
