package br.senac.menota.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "empresario_id", referencedColumnName = "id")
    @JsonBackReference
    private Empresario empresario;

    @PrePersist
    public void onCreate(){
        this.dataCadastro = LocalDateTime.now();
    }
}
