package br.senac.menota.dtos;

import java.time.LocalDateTime;

public record UserLoginResponseDTO(
        String token,
        LocalDateTime expiresAt,
        String accountType
){
}
