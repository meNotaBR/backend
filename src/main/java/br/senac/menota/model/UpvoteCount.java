package br.senac.menota.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_upvote_count")
public class UpvoteCount extends EntityID{

    @OneToOne
    @JoinColumn(name = "projeto_id", referencedColumnName = "id")
    private Projeto projeto;

    private int totalUpvotes;
}
