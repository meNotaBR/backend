package br.senac.menota.services;

import br.senac.menota.model.Projeto;
import br.senac.menota.repositories.ProjetoRepository;
import br.senac.menota.repositories.StartupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public Projeto create(Projeto projeto){
        return projetoRepository.save(projeto);
    }
}
