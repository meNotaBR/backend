package br.senac.menota.dtos;

import br.senac.menota.model.BaseUser;
import br.senac.menota.model.Empresario;
import br.senac.menota.model.Investidor;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private String id;
    private String nome;
    private String sobrenome;
    private String email;
    private String numeroCelular;
    private LocalDate dataNasc;
    private String userType;

    public static UserProfileResponseDTO fromEntity(BaseUser user) {
        UserProfileResponseDTO dto = new UserProfileResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUserType(user.getUserType());

        if (user instanceof Empresario empresario) {
            dto.setNome(empresario.getNome());
            dto.setSobrenome(empresario.getSobrenome());
            dto.setNumeroCelular(empresario.getNumeroCelular());
            dto.setDataNasc(empresario.getDataNasc());
        } else if (user instanceof Investidor investidor) {
            dto.setNome(investidor.getNome());
            dto.setSobrenome(investidor.getSobrenome());
            dto.setNumeroCelular(investidor.getNumeroCelular());
            dto.setDataNasc(investidor.getDataNasc());
        }

        return dto;
    }
} 