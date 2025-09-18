package com.tchindaClovis.gestiondestock.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tchindaClovis.gestiondestock.model.Entreprise;
import com.tchindaClovis.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class FournisseurDto {

    private Integer id;

    private String nomFournisseur;

    private String photo;

    private String email;

    private String numTel;

    private AdresseDto adresse;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity (Fournisseur fournisseur){  //permet de faire un mapping de l'entité vers le DTO
        if(fournisseur == null){
            return null;
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nomFournisseur(fournisseur.getNomFournisseur())
                .photo(fournisseur.getPhoto())
                .email(fournisseur.getEmail())
                .numTel(fournisseur.getNumTel())
                .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .build();
    }

    public static Fournisseur toEntity (FournisseurDto fournisseurDto){    //permet de faire un mapping du DTO vers l'entité
        if(fournisseurDto == null){
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNomFournisseur(fournisseurDto.getNomFournisseur());
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setNumTel(fournisseurDto.getNumTel());
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresse()));

        return fournisseur;
    }
}
