package br.senac.menota.resources;

import br.senac.menota.model.Investidor;
import br.senac.menota.services.InvestidorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investor")
public class InvestidorController {

    private final InvestidorService investidorService;

    public InvestidorController(InvestidorService investidorService) {
        this.investidorService = investidorService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Investidor create(@RequestBody Investidor request){
        return investidorService.create(request);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Investidor> getAll(){
        return investidorService.getAllInvestors();
    }

}
