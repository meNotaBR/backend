package br.senac.menota.dtos;

import br.senac.menota.model.Empresario;
import br.senac.menota.model.Localizacao;
import br.senac.menota.model.Startup;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresarioCreateRequestDTO {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String senha;
    private String numeroCelular;
    private LocalDate dataNasc;
    private String nomeFantasia;
    private String cnpj;
    private String cidade;
    private String estado;
    private String profileImage;

    public static Empresario fromEntity(EmpresarioCreateRequestDTO requestDTO){
        var empresario =  Empresario.builder()
                .nome(requestDTO.getNome())
                .sobrenome(requestDTO.getSobrenome())
                .cpf(requestDTO.getCpf())
                .numeroCelular(requestDTO.getNumeroCelular())
                .dataNasc(requestDTO.getDataNasc())
                .build();

        empresario.setEmail(requestDTO.getEmail());
        empresario.setSenha(new BCryptPasswordEncoder().encode(requestDTO.getSenha()));

        return empresario;
    }

    public static Startup fromEntityStartup(EmpresarioCreateRequestDTO requestDTO){
        return Startup.builder()
                .nomeFantasia(requestDTO.getNomeFantasia())
                .cnpj(requestDTO.getCnpj())
                .localizacao(Localizacao.builder()
                        .cidade(requestDTO.getCidade())
                        .estado(requestDTO.getEstado())
                        .build())
                .build();
    }
}
