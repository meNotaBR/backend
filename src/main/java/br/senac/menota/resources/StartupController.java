package br.senac.menota.resources;

import br.senac.menota.model.Startup;
import br.senac.menota.services.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/startup")
@RequiredArgsConstructor
public class StartupController {

    private final StartupService startupService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Startup create(@RequestBody Startup startup){
        return startupService.create(startup);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Startup> getAllStartups(){
        return startupService.getAllStartups();
    }
}
