package br.senac.menota.strategy.empresario;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Empresario;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.strategy.NewEmpresarioValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateEmail implements NewEmpresarioValidationStrategy {

    private final EmpresarioRepository empresarioRepository;

    @Override
    public void validate(Empresario empresario) {

        if (empresario.getEmail() == null || empresario.getEmail().isBlank()){
            throw new ValidationException("Por favor, informe seu email!");
        }

        if (empresarioRepository.findByEmail(empresario.getEmail()) != null){
            throw new ValidationException("Email já cadastrado!");
        }
    }
}
