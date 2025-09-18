package com.tchindaClovis.gestiondestock.model;

import com.tchindaClovis.gestiondestock.dto.VenteDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "LigneVente")  //optionnel car par défaut le nom de la classe
public class LigneVente extends AbstractEntity{

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "identreprise")  //entité de convenance qu'on ajoute juste pour certaines dispositions
    private Integer idEntreprise;  //rien à voir avec les règle UML

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idvente")
    private Vente vente;

    public VenteDto fromEntity (Vente vente){  //permet de faire un mapping de l'entité vers le DTO
        if(vente == null){
            return null;
        }

        return VenteDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .commentaire(vente.getCommentaire())
                .build();
    }

    public Vente toEntity (VenteDto venteDto){    //permet de faire un mapping du DTO vers l'entité
        if(venteDto == null){
            return null;
        }
        Vente vente = new Vente();
        vente.setId(venteDto.getId());
        vente.setCode(venteDto.getCode());
        vente.setDateVente(venteDto.getDateVente());
        vente.setCommentaire(venteDto.getCommentaire());

        return vente;
    }
}
