package br.senac.menota.strategy.projeto;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Projeto;
import br.senac.menota.strategy.NewProjetoValidationStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidateDataPrevista implements NewProjetoValidationStrategy {
    @Override
    public void validate(Projeto projeto) {

        if (projeto.getDataPrevistaInicio() == null){
            throw new ValidationException("Data prevista de Início não pode estar em branco");
        }

        if (projeto.getDataPrevistaEntrega() == null){
            throw new ValidationException("Data prevista de Entrega não pode estar em branco");
        }

        if (projeto.getDataPrevistaInicio().isBefore(LocalDate.now())){
            throw new ValidationException("Data prevista de Início não pode ser menor que a data atual!");
        }

        if (projeto.getDataPrevistaEntrega().isBefore(LocalDate.now())){
            throw new ValidationException("Data prevista de Entrega não pode ser menor que a data atual!");
        }

        if (projeto.getDataPrevistaEntrega().isBefore(projeto.getDataPrevistaInicio())){
            throw new ValidationException("Data prevista de Entrega não pode ser menor que a Data prevista de Início");
        }
    }
}