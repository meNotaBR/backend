package br.senac.menota.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tb_projeto_capa")
public class ProjetoImagemCapa extends EntityID{

    @OneToOne(targetEntity = Projeto.class)
    private Projeto projeto;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String imageBase64;
}
