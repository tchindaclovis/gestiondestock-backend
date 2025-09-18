package com.tchindaClovis.gestiondestock.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tchindaClovis.gestiondestock.model.*;
import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String nomUtilisateur;

    private String prenomUtilisateur;

    private String email;

    private String dateDeNaissance;

    private String motDePasse;

    private String photo;

    private AdresseDto adresse;

    private EntrepriseDto entreprise;

    @JsonIgnore
    private List<RolesDto> roles;

    public static UtilisateurDto fromEntity (Utilisateur utilisateur){  //permet de faire un mapping de l'entité vers le DTO
        if(utilisateur == null){
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nomUtilisateur(utilisateur.getNomUtilisateur())
                .prenomUtilisateur(utilisateur.getPrenomUtilisateur())
                .email(utilisateur.getEmail())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .motDePasse(utilisateur.getMotDePasse())
                .photo(utilisateur.getPhoto())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .build();
    }

    public static Utilisateur toEntity (UtilisateurDto utilisateurDto){    //permet de faire un mapping du DTO vers l'entité
        if(utilisateurDto == null){
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNomUtilisateur(utilisateurDto.getNomUtilisateur());
        utilisateur.setPrenomUtilisateur(utilisateurDto.getPrenomUtilisateur());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));

        return utilisateur;
    }

}
