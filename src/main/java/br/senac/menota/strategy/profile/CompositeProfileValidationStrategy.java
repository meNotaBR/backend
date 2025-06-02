package br.senac.menota.strategy.profile;

import br.senac.menota.model.BaseUser;
import br.senac.menota.strategy.UserProfileValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompositeProfileValidationStrategy implements UserProfileValidationStrategy {

    private final List<UserProfileValidationStrategy> validationStrategies;

    @Override
    public void validate(BaseUser user) {
        validationStrategies.stream()
                .filter(strategy -> !(strategy instanceof CompositeProfileValidationStrategy))
                .forEach(strategy -> strategy.validate(user));
    }
} 