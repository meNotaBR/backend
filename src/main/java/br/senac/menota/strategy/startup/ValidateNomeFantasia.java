package br.senac.menota.strategy.startup;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Startup;
import br.senac.menota.repositories.StartupRepository;
import br.senac.menota.strategy.NewStartupValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateNomeFantasia implements NewStartupValidationStrategy {

    private final StartupRepository startupRepository;

    @Override
    public void validate(Startup startup) {
        if (startupRepository.findByNomeFantasia(startup.getNomeFantasia()).isPresent()){
            throw new ValidationException("Já existe uma empresa cadastrada com este nome fantasia");
        }
    }
}
