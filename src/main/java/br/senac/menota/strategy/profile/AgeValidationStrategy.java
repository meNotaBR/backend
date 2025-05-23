package br.senac.menota.strategy.profile;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.BaseUser;
import br.senac.menota.model.Empresario;
import br.senac.menota.model.Investidor;
import br.senac.menota.strategy.UserProfileValidationStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeValidationStrategy implements UserProfileValidationStrategy {

    @Override
    public void validate(BaseUser user) {
        LocalDate dataNasc = null;
        
        if (user instanceof Empresario empresario) {
            dataNasc = empresario.getDataNasc();
        } else if (user instanceof Investidor investidor) {
            dataNasc = investidor.getDataNasc();
        }

        if (dataNasc == null) {
            throw new ValidationException("Data de nascimento é obrigatória");
        }

        int idade = Period.between(dataNasc, LocalDate.now()).getYears();
        if (idade < 18) {
            throw new ValidationException("Usuário deve ter no mínimo 18 anos");
        }
    }
} 