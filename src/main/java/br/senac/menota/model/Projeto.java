package br.senac.menota.model;

import br.senac.menota.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_projeto")
public class Projeto extends EntityID{

    private String nome;
    private LocalDate dataPrevistaInicio;
    private LocalDate dataInicio;
    private LocalDate dataPrevistaEntrega;
    private LocalDate dataEntrega;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "startup_id", referencedColumnName = "id")
    private Startup startup;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "projeto")
    private List<Entregavel> entregaveis;
}
