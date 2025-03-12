package br.senac.menota.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "tb_investidor")
public class Investidor extends EntityID{

    private String nome;
    private String sobrenome;
    private String cpf;
    private String cnpj;
    private LocalDateTime dataCadastro;
    private LocalDate dataNasc;


    public Investidor(){
        this.dataCadastro = LocalDateTime.now();
    }
}
