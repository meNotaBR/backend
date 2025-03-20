package br.senac.menota.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_upvote")
public class Upvote extends EntityID{

    @ManyToOne
    @JoinColumn(name = "investidor_id", referencedColumnName = "id")
    private Investidor investidor;

    @ManyToOne
    @JoinColumn(name = "projeto_id", referencedColumnName = "id")
    private Projeto projeto;

    private LocalDateTime dataCriacao;

    @PrePersist
    private void onCreate(){
        this.dataCriacao = LocalDateTime.now();
    }
}
