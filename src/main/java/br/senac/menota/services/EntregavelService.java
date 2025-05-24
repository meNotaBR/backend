package br.senac.menota.services;

import br.senac.menota.model.Entregavel;
import br.senac.menota.repositories.EntregavelRepository;
import br.senac.menota.strategy.NewEntregavelValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregavelService {

    private final EntregavelRepository entregavelRepository;
    private final List<NewEntregavelValidationStrategy> newEntregavelValidationStrategies;

    public Entregavel create(Entregavel entregavel){

        newEntregavelValidationStrategies.forEach(validation -> validation.validate(entregavel));

        return entregavelRepository.save(entregavel);
    }
}
