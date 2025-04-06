package br.senac.menota.services;

import br.senac.menota.dtos.EmpresarioCreateResponseDTO;
import br.senac.menota.model.Empresario;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.strategy.NewEmpresarioValidationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmpresarioService {

    private final EmpresarioRepository empresarioRepository;
    private final List<NewEmpresarioValidationStrategy> newEmpresarioValidationStrategies;

    public EmpresarioCreateResponseDTO create(Empresario empresario){

        newEmpresarioValidationStrategies.forEach(validation -> validation.validate(empresario));

        empresario.setSenha(new BCryptPasswordEncoder().encode(empresario.getSenha()));

        return EmpresarioCreateResponseDTO.fromEntity(empresarioRepository.save(empresario));
    }

    public List<Empresario> getAll(){
        return empresarioRepository.findAll();
    }
}
