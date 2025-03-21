package br.senac.menota.strategy.entregavel;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Entregavel;
import br.senac.menota.strategy.NewEntregavelValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidateProjeto implements NewEntregavelValidationStrategy {

    @Override
    public void validate(Entregavel entregavel) {
        if (entregavel.getProjeto() == null){
            throw new ValidationException("Você deve vincular um projeto a esta tarefa!");
        }
    }
}
