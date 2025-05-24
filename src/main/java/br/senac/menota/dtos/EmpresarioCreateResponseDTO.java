package br.senac.menota.dtos;

import br.senac.menota.model.Empresario;
import br.senac.menota.model.Investidor;

import java.time.LocalDate;

public record EmpresarioCreateResponseDTO(
        String nome,
        String sobrenome,
        String cpf,
        String email,
        LocalDate dataCadastro,
        LocalDate dataNasc
) {
    public static EmpresarioCreateResponseDTO fromEntity(Empresario empresario){
        return new EmpresarioCreateResponseDTO(
                empresario.getNome(),
                empresario.getSobrenome(),
                empresario.getCpf(),
                empresario.getEmail(),
                empresario.getDataCadastro(),
                empresario.getDataNasc()
        );
    }
}
