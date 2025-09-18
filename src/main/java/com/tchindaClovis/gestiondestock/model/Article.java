package com.tchindaClovis.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Article")  //optionnel car par défaut le nom de la classe
public class Article extends AbstractEntity{
    @Column(name = "codearticle")
    private String codeArticle;

    @Column(name = "gesignation")
    private String designation;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxtva")
    private BigDecimal tauxTva;

    @Column(name = "prixunitairettc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "photo")
    private String photo;  //String parceque ça ne sera pas stocké dans la BDD mais dans le cloud

    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "identreprise")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "article")
    private List<MvtStock> mvtStocks;

    @OneToMany(mappedBy = "article")
    private List<LigneVente> ligneVentes;
}
