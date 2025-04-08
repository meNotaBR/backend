package br.senac.menota.dtos;

import br.senac.menota.enums.Status;
import br.senac.menota.model.Projeto;
import br.senac.menota.model.Startup;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProjetoFeedResponseDTO(
        Long id,
        String nome,
        LocalDate dataPrevistaInicio,
        LocalDate dataPrevistaEntrega,
        LocalDateTime dataCadastro,
        Boolean isPelando,
        Status status,
        String descricao,
        Startup startup,
        int upvotes,
        boolean isLiked
) {

    public static ProjetoFeedResponseDTO fromEntity(Projeto projeto, boolean isLiked){
        return new ProjetoFeedResponseDTO(
                projeto.getId(),
                projeto.getNome(),
                projeto.getDataPrevistaInicio(),
                projeto.getDataPrevistaEntrega(),
                projeto.getDataCadastro(),
                projeto.getIsPelando(),
                projeto.getStatus(),
                projeto.getDescricao(),
                projeto.getStartup(),
                projeto.getUpvoteCount().getCount(),
                isLiked
        );
    }
}
