package br.senac.menota.services;

import br.senac.menota.model.Entregavel;
import br.senac.menota.repositories.EntregavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntregavelService {

    private final EntregavelRepository entregavelRepository;

    public Entregavel create(Entregavel entregavel){
        return entregavelRepository.save(entregavel);
    }
}
