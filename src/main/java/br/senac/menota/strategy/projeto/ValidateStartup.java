package br.senac.menota.strategy.projeto;

import br.senac.menota.model.Projeto;
import br.senac.menota.services.StartupService;
import br.senac.menota.strategy.NewProjetoValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ValidateStartup implements NewProjetoValidationStrategy {

    private final StartupService startupService;

    @Override
    public void validate(Projeto projeto) {
        startupService.existsById(projeto.getStartup().getId());
    }
}
