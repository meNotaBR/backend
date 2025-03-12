package br.senac.menota.services;

import br.senac.menota.model.Investidor;
import br.senac.menota.repositories.InvestidorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InvestidorService {

    private final InvestidorRepository investidorRepository;

    public InvestidorService(InvestidorRepository investidorRepository) {
        this.investidorRepository = investidorRepository;
    }

    public Investidor create(Investidor request){
        return investidorRepository.save(request);
    }

    public List<Investidor> getAllInvestors(){
        return investidorRepository.findAll();
    }
}
