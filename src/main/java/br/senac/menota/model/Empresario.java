package br.senac.menota.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_empresario")
public class Empresario extends BaseUser{

    private String nome;
    private String sobrenome;
    private String cpf;
    private String numeroCelular;
    private LocalDate dataCadastro;
    private LocalDate dataNasc;

    @PrePersist
    protected void onCreate(){
        this.dataCadastro = LocalDate.now();
        this.setUserType("EMPRESARIO");
    }
}