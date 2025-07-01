package br.senac.menota.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileUpdateRequestDTO {
    private String nome;
    private String sobrenome;
    private String email;
    private String numeroCelular;
    private LocalDate dataNasc;
} 