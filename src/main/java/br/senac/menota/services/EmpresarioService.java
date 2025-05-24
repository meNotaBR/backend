package br.senac.menota.services;

import br.senac.menota.dtos.EmpresarioCreateRequestDTO;
import br.senac.menota.dtos.EmpresarioCreateResponseDTO;
import br.senac.menota.model.Empresario;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.repositories.StartupRepository;
import br.senac.menota.strategy.NewEmpresarioValidationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmpresarioService {

    private final EmpresarioRepository empresarioRepository;
    private final StartupRepository startupRepository;
    private final List<NewEmpresarioValidationStrategy> newEmpresarioValidationStrategies;

    public EmpresarioCreateResponseDTO create(EmpresarioCreateRequestDTO request){

        var empresarioFromRequest = EmpresarioCreateRequestDTO.fromEntity(request);

        newEmpresarioValidationStrategies.forEach(validation -> validation.validate(empresarioFromRequest));

        var empresario = empresarioRepository.save(empresarioFromRequest);

        var startup = EmpresarioCreateRequestDTO.fromEntityStartup(request);

        startup.setEmpresario(empresario);

        startupRepository.save(startup);

        return EmpresarioCreateResponseDTO.fromEntity(empresario);
    }

    public List<Empresario> getAll(){
        return empresarioRepository.findAll();
    }
}
