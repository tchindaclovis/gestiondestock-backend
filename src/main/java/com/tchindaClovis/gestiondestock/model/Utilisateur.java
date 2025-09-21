package com.tchindaClovis.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Utilisateur")  //optionnel car par défaut le nom de la classe
public class Utilisateur extends AbstractEntity{

    @Column(name = "nomUtilisateur")
    private String nomUtilisateur;

    @Column(name = "prenomUtilisateur")
    private String prenomUtilisateur;

    @Column(name = "email")
    private String email;

    @Column(name = "datedenaissance")
    private String dateDeNaissance;

    @Column(name = "motdepasse")
    private String motDePasse;

    @Embedded //champ embarqué qui peut être utilisé dan plusieurs autres classes
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "utilisateur")
    private List<Roles> roles;

    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;

}
