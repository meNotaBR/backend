package br.senac.menota.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class EntityID {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
