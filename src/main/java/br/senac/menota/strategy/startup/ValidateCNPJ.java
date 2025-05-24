package br.senac.menota.strategy.startup;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Startup;
import br.senac.menota.repositories.StartupRepository;
import br.senac.menota.strategy.NewStartupValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateCNPJ implements NewStartupValidationStrategy {

    private final StartupRepository startupRepository;

    @Override
    public void validate(Startup startup) {
        if (startupRepository.findByCnpj(startup.getCnpj()).isPresent()){
            throw new ValidationException("Já existe uma empresa cadastrada com este CNPJ");
        }
    }
}
