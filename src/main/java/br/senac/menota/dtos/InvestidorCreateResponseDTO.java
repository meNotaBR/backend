package br.senac.menota.dtos;

import br.senac.menota.model.Investidor;

import java.time.LocalDate;

public record InvestidorCreateResponseDTO(
        String nome,
        String sobrenome,
        String cpf,
        String email,
        LocalDate dataCadastro,
        LocalDate dataNasc
) {

    public static InvestidorCreateResponseDTO fromEntity(Investidor investidor){
        return new InvestidorCreateResponseDTO(
                investidor.getNome(),
                investidor.getSobrenome(),
                investidor.getCpf(),
                investidor.getEmail(),
                investidor.getDataCadastro(),
                investidor.getDataNasc()
        );
    }
}
