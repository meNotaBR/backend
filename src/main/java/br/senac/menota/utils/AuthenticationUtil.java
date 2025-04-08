package br.senac.menota.utils;

import br.senac.menota.model.BaseUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {

    private AuthenticationUtil(){
        throw new IllegalStateException("Utility Class");
    }

    public static BaseUser retriveAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (BaseUser) authentication.getPrincipal();
    }
}