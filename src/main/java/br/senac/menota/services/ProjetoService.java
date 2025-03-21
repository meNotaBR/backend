package br.senac.menota.services;

import br.senac.menota.model.Projeto;
import br.senac.menota.repositories.ProjetoRepository;
import br.senac.menota.strategy.NewProjetoValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final List<NewProjetoValidationStrategy> newProjetoValidationStrategies;

    public Projeto create(Projeto projeto){

        newProjetoValidationStrategies.forEach(validation -> validation.validate(projeto));

        return projetoRepository.save(projeto);
    }

    public List<Projeto> getAll(){
        return projetoRepository.findAll();
    }
}
