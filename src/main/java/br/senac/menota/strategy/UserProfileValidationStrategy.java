package br.senac.menota.strategy;

import br.senac.menota.model.BaseUser;

public interface UserProfileValidationStrategy {
    void validate(BaseUser user);
} 