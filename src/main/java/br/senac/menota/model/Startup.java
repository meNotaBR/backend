package br.senac.menota.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_startup")
public class Startup extends EntityID{

    private String nomeFantasia;
    private String cnpj;
    private LocalDateTime dataCadastro;
    private LocalDate dataCriacao;

    @PrePersist
    public void onCreate(){
        this.dataCadastro = LocalDateTime.now();
    }
}
