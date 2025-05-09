package br.senac.menota.model;

import br.senac.menota.dtos.UpvoteGroupedByDate;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Entity
@Immutable
@Table(name = "vw_advanced_project_dashboard")
@Getter
public class DashboardView {

    @Id
    @Column(name = "project_id")
    private Long id;

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

    @Column(name = "tempo_medio_entrega")
    private Double tempoMedioEntrega;

    @Column(name = "desvio_padrao_tempo_entrega")
    private Double desvioPadraoTempoEntrega;

    @Column(name = "taxa_conclusao_entregaveis")
    private Double taxaConclusaoEntregaveis;

    @Column(name = "entregaveis_em_andamento")
    private Long entregaveisEmAndamento;

    @Column(name = "upvote_count")
    private Long totalUpvotes;

    @Column(name = "upvotes_ultimo_mes")
    private Long upvotesUltimoMes;

    @Column(name = "taxa_crescimento_upvotes")
    private Double taxaCrescimentoUpvotes;

    @Column(name = "entregaveis_este_mes")
    private Long entregaveisEsteMes;

    @Transient
    private List<UpvoteGroupedByDate> upvotes;

    public List<UpvoteGroupedByDate> getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(List<UpvoteGroupedByDate> upvotes) {
        this.upvotes = upvotes;
    }
}
