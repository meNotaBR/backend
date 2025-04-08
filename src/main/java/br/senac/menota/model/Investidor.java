package br.senac.menota.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_investidor")
public class Investidor extends BaseUser{

    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataCadastro;
    private LocalDate dataNasc;


    @PrePersist
    protected void onCreate(){
        this.dataCadastro = LocalDate.now();
        this.setUserType("INVESTIDOR");
    }
}
