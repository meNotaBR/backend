package br.senac.menota.strategy.projeto;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Projeto;
import br.senac.menota.strategy.NewProjetoValidationStrategy;

import java.time.LocalDate;

public class ValidateDataPrevistaInicio implements NewProjetoValidationStrategy {


    @Override
    public void validate(Projeto projeto) {

        if (projeto.getDataPrevistaInicio() == null){
            throw new ValidationException("Data prevista de Início não pode estar em branco!");
        }

        if (projeto.getDataPrevistaInicio().isBefore(LocalDate.now())){
            throw new ValidationException("Data prevista de Início não pode ser menor que a data atual!");
        }

    }
}
