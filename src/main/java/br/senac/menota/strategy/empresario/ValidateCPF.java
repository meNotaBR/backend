package br.senac.menota.strategy.empresario;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Empresario;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.strategy.NewEmpresarioValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateCPF implements NewEmpresarioValidationStrategy {

    private final EmpresarioRepository empresarioRepository;

    @Override
    public void validate(Empresario empresario) {

        if (empresario.getCpf() == null || empresario.getCpf().isBlank()){
            throw new ValidationException("Por favor, informe seu CPF");
        }

        if (empresarioRepository.findByCpf(empresario.getCpf()).isPresent()){
            throw new ValidationException("CPF já cadastrado!");
        }
    }
}
