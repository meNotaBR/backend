package br.senac.menota.services;

import br.senac.menota.configs.TokenService;
import br.senac.menota.dtos.UserLoginRequestDTO;
import br.senac.menota.dtos.UserLoginResponseDTO;
import br.senac.menota.model.BaseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserLoginResponseDTO login(UserLoginRequestDTO user){
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.email(), user.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        return new UserLoginResponseDTO(tokenService.generateToken((BaseUser) auth.getPrincipal()), LocalDateTime.now().plusMinutes(120));
    }
}
