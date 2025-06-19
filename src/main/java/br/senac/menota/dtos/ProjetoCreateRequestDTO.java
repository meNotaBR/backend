package br.senac.menota.dtos;

import java.time.LocalDate;

public record ProjetoCreateRequestDTO(
        String nome,
        LocalDate dataPrevistaInicio,
        LocalDate dataPrevistaEntrega,
        String descricao,
        String imageBase64
) {
}
