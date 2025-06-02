package br.senac.menota.services;

import br.senac.menota.exceptions.NotFoundException;
import br.senac.menota.model.Startup;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.repositories.StartupRepository;
import br.senac.menota.strategy.NewStartupValidationStrategy;
import br.senac.menota.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartupService {

    private final StartupRepository startupRepository;
    private final EmpresarioRepository empresarioRepository;
    private final List<NewStartupValidationStrategy> newStartupValidationStrategies;

    public Startup create(Startup request){

        var empresario = empresarioRepository.findById(AuthenticationUtil.retriveAuthenticatedUser().getId()).orElseThrow(() -> new NotFoundException("Empresário não encontrado"));

        request.setEmpresario(empresario);

        newStartupValidationStrategies.forEach(validation -> validation.validate(request));

        return startupRepository.save(request);
    }

    public List<Startup> getAllStartups(){
        return startupRepository.findAll();
    }

    @Transactional
    public Startup getStartupById(Long id) {
        return startupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Startup não encontrada com o ID: " + id));
    }

    public void existsById(Long id){
        if (!startupRepository.existsById(id)){
            throw new NotFoundException("Startup não cadastrada!");
        }
    }
}
