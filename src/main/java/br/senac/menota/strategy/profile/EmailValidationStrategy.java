package br.senac.menota.strategy.profile;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.BaseUser;
import br.senac.menota.model.Empresario;
import br.senac.menota.model.Investidor;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.repositories.InvestidorRepository;
import br.senac.menota.strategy.UserProfileValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class EmailValidationStrategy implements UserProfileValidationStrategy {

    private final EmpresarioRepository empresarioRepository;
    private final InvestidorRepository investidorRepository;
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public void validate(BaseUser user) {
        String email = user.getEmail();
        
        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
            throw new ValidationException("E-mail inválido");
        }

        Optional<Empresario> empresario = Optional.ofNullable(empresarioRepository.findByEmail(email));
        if (empresario.isPresent() && !empresario.get().getId().equals(user.getId())) {
            throw new ValidationException("E-mail já está em uso");
        }

        Optional<Investidor> investidor = Optional.ofNullable(investidorRepository.findByEmail(email));
        if (investidor.isPresent() && !investidor.get().getId().equals(user.getId())) {
            throw new ValidationException("E-mail já está em uso");
        }
    }
} 