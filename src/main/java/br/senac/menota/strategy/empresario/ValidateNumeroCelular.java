package br.senac.menota.strategy.empresario;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Empresario;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.strategy.NewEmpresarioValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateNumeroCelular implements NewEmpresarioValidationStrategy {

    private final EmpresarioRepository empresarioRepository;

    @Override
    public void validate(Empresario empresario) {

        if (empresario.getNumeroCelular() == null || empresario.getNumeroCelular().isBlank()){
            throw new ValidationException("Por favor, informe seu contato!");
        }

        if (empresarioRepository.findByNumeroCelular(empresario.getNumeroCelular()).isPresent()){
            throw new ValidationException("Número de Celular já cadastrado");
        }
    }
}
