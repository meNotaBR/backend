package br.senac.menota.dtos;

public record ProjetoCarrosselResponseDTO(
        Long id,
        String nome,
        String img,
        String descricao,
        int totalUpvote
) {
}
