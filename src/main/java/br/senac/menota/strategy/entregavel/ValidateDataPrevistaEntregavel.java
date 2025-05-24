package br.senac.menota.strategy.entregavel;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Entregavel;
import br.senac.menota.strategy.NewEntregavelValidationStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidateDataPrevistaEntregavel implements NewEntregavelValidationStrategy {

    @Override
    public void validate(Entregavel entregavel) {

        if (entregavel.getDataPrevistaInicio() == null){
            throw new ValidationException("Data prevista de Início não pode estar em branco");
        }

        if (entregavel.getDataPrevistaEntrega() == null){
            throw new ValidationException("Data prevista de Entrega não pode estar em branco");
        }

        if (entregavel.getDataPrevistaInicio().isBefore(LocalDate.now())){
            throw new ValidationException("Data prevista de Início não pode ser menor que a data atual!");
        }

        if (entregavel.getDataPrevistaEntrega().isBefore(LocalDate.now())){
            throw new ValidationException("Data prevista de Entrega não pode ser menor que a data atual!");
        }

        if (entregavel.getDataPrevistaEntrega().isBefore(entregavel.getDataPrevistaInicio())){
            throw new ValidationException("Data prevista de Entrega não pode ser menor que a Data prevista de Início");
        }
    }
}
