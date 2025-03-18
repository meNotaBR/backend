package br.senac.menota.strategy;

import br.senac.menota.model.Projeto;
import org.springframework.stereotype.Component;

@Component
public interface NewProjetoValidationStrategy {
    void validate(Projeto projeto);
}
