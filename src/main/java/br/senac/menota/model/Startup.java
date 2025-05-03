package br.senac.menota.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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

    @Lob
    @Column(columnDefinition = "TEXT")
    private String profileImage;

    @OneToOne
    @JoinColumn(name = "empresario_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Empresario empresario;

    @PrePersist
    public void onCreate(){
        this.dataCadastro = LocalDateTime.now();
    }
}
