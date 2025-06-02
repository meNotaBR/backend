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
public class PhoneValidationStrategy implements UserProfileValidationStrategy {

    private final EmpresarioRepository empresarioRepository;
    private final InvestidorRepository investidorRepository;
    
    private static final String PHONE_REGEX = "^\\+?[1-9][0-9]{10,14}$";

    @Override
    public void validate(BaseUser user) {
        String phone = null;
        
        if (user instanceof Empresario empresario) {
            phone = empresario.getNumeroCelular();
        } else if (user instanceof Investidor investidor) {
            phone = investidor.getNumeroCelular();
        }

        if (phone == null || !Pattern.matches(PHONE_REGEX, phone)) {
            throw new ValidationException("Número de celular inválido");
        }

        Optional<Empresario> empresario = empresarioRepository.findByNumeroCelular(phone);
        if (empresario.isPresent() && !empresario.get().getId().equals(user.getId())) {
            throw new ValidationException("Número de celular já está em uso");
        }

    }
} 