package br.senac.menota.services;

import br.senac.menota.exceptions.NotFoundException;
import br.senac.menota.model.Startup;
import br.senac.menota.repositories.StartupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartupService {

    private final StartupRepository startupRepository;

    public Startup create(Startup request){
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
