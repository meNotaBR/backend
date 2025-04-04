package br.senac.menota.model;

import br.senac.menota.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(length = 1000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "startup_id", referencedColumnName = "id")
    private Startup startup;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "projeto")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //read only pq não vamos criar entregáveis ao cadastrar um projeto, será criado em outra tela/modal
    private List<Entregavel> entregaveis;

    @OneToMany(mappedBy = "projeto")
    private List<Upvote> upvote;
}
