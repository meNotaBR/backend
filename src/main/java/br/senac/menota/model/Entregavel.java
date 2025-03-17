package br.senac.menota.model;

import br.senac.menota.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_entregavel")
public class Entregavel extends EntityID{

    private String nome;
    private LocalDate dataPrevistaInicio;
    private LocalDate dataInicio;
    private LocalDate dataPrevistaEntrega;
    private LocalDate dataEntrega;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //write only pq não precisamos retornar o projeto vinculado no get list todas as vezes
    private Projeto projeto;
}
