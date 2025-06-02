package br.senac.menota.strategy.profile;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.BaseUser;
import br.senac.menota.model.Empresario;
import br.senac.menota.model.Investidor;
import br.senac.menota.strategy.UserProfileValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class NameValidationStrategy implements UserProfileValidationStrategy {

    @Override
    public void validate(BaseUser user) {
        String nome = "";
        String sobrenome = "";
        
        if (user instanceof Empresario empresario) {
            nome = empresario.getNome();
            sobrenome = empresario.getSobrenome();
        } else if (user instanceof Investidor investidor) {
            nome = investidor.getNome();
            sobrenome = investidor.getSobrenome();
        }

        if (nome == null || nome.length() < 3) {
            throw new ValidationException("O nome deve ter no mínimo 3 caracteres");
        }
        
        if (sobrenome == null || sobrenome.length() < 3) {
            throw new ValidationException("O sobrenome deve ter no mínimo 3 caracteres");
        }
    }
} 