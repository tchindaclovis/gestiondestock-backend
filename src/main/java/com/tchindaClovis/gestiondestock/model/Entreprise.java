package com.tchindaClovis.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Entreprise")  //optionnel car par défaut le nom de la classe
public class Entreprise extends AbstractEntity{

    @Column(name = "nomEntreprise")
    private String nomEntreprise;

    @Column(name = "description")
    private String description;

    @Embedded
    private Adresse adresse;

    @Column(name = "codeFiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "siteWeb")
    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Article> articles;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;

}
