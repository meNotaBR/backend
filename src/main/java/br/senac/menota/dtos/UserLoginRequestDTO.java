package br.senac.menota.dtos;

public record UserLoginRequestDTO (
        String email,
        String senha
) {
}
