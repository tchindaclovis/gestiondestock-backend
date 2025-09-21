package com.tchindaClovis.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Roles")  //optionnel car par défaut le nom de la classe
public class Roles extends AbstractEntity{

    @Column(name = "roleName")
    private String roleName;

    @Column(name = "idEntreprise")  //entité de convenance qu'on ajoute juste pour certaines dispositions
    private Integer idEntreprise;  //rien à voir avec les règle UML

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;

}
