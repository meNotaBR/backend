package br.senac.menota.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne(mappedBy = "empresario", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Startup startup;

    @PrePersist
    protected void onCreate(){
        this.dataCadastro = LocalDate.now();
    }
}