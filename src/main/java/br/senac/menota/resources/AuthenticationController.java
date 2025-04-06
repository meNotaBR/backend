package br.senac.menota.resources;

import br.senac.menota.dtos.UserLoginRequestDTO;
import br.senac.menota.dtos.UserLoginResponseDTO;
import br.senac.menota.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoginResponseDTO login(@RequestBody UserLoginRequestDTO request){
        return loginService.login(request);
    }
}
