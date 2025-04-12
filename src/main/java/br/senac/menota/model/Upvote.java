package br.senac.menota.model;


import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String userId;

    @ManyToOne
    @JoinColumn(name = "projeto_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Projeto projeto;

    private LocalDateTime dataCriacao;

    @PrePersist
    private void onCreate(){
        this.dataCriacao = LocalDateTime.now();
    }
}
