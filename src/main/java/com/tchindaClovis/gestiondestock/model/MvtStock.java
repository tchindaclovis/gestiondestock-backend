package com.tchindaClovis.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MvtStock")  //optionnel car par défaut le nom de la classe
public class MvtStock extends AbstractEntity {

    @Column(name = "dateMvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "typemvtstock")
    private TypeMvtStock typeMvtStock;

    @Column(name = "idEntreprise")  //entité de convenance qu'on ajoute juste pour certaines dispositions
    private Integer idEntreprise;  //rien à voir avec les règle UML

    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;

}
