package br.senac.menota.services;

import br.senac.menota.exceptions.NotFoundException;
import br.senac.menota.model.Startup;
import br.senac.menota.repositories.StartupRepository;
import br.senac.menota.strategy.NewStartupValidationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartupService {

    private final StartupRepository startupRepository;
    private final List<NewStartupValidationStrategy> newStartupValidationStrategies;

    public Startup create(Startup request){

        newStartupValidationStrategies.forEach(validation -> validation.validate(request));

        return startupRepository.save(request);
    }

    public List<Startup> getAllStartups(){
        return startupRepository.findAll();
    }

    public void existsById(Long id){
        if (!startupRepository.existsById(id)){
            throw new NotFoundException("Startup não cadastrada!");
        }
    }
}
