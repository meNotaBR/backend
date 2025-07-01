package br.senac.menota.model;

import br.senac.menota.dtos.UpvoteGroupedByDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Entity
@Immutable
@Table(name = "vw_projetos")
@Getter
public class DashboardView {

    @Id
    @Column(name = "project_id")
    private Long id;

    @Column(name = "projeto_nome")
    private String nome;

    @Column(name = "projeto_descricao")
    private String descricao;

    @Column(name = "data_inicio")
    private String dataInicio;

    @Column(name = "data_entrega")
    private String dataEntrega;

    @Column(name = "total_entregaveis")
    private Long totalEntregaveis;

    @Column(name = "entregues_no_prazo")
    private Long entreguesNoPrazo;

    @Column(name = "entregues_com_atraso")
    private Long entreguesComAtraso;

    @Column(name = "em_atraso")
    private Long emAtraso;

    @Column(name = "indice_desempenho_prazo")
    private Double indiceDesempenhoPrazo;

    @Column(name = "taxa_conclusao_entregaveis")
    private Double taxaConclusaoEntregaveis;

    @Column(name = "entregaveis_em_andamento")
    private Long entregaveisEmAndamento;

    @Column(name = "upvote_count")
    private Long totalUpvotes;

    @Column(name = "taxa_crescimento_upvotes")
    private Double taxaCrescimentoUpvotes;

    @Transient
    @Setter
    private List<UpvoteGroupedByDate> upvotes;

    @Transient
    @Setter
    private List<Entregavel> entregaveis;
}
