package com.tchindaClovis.gestiondestock.dto;
import com.tchindaClovis.gestiondestock.model.Article;
import com.tchindaClovis.gestiondestock.model.CommandeFournisseur;
import com.tchindaClovis.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
@Data
@Builder
public class LigneCommandeFournisseurDto {

    private Integer id;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur;

    public static LigneCommandeFournisseurDto fromEntity (LigneCommandeFournisseur ligneCommandeFournisseur){  //permet de faire un mapping de l'entité vers le DTO
        if(ligneCommandeFournisseur == null){
            return null;
        }

        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .commandeFournisseur(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
                .build();
    }

    public static LigneCommandeFournisseur toEntity (LigneCommandeFournisseurDto ligneCommandeFournisseurDto){    //permet de faire un mapping du DTO vers l'entité
        if(ligneCommandeFournisseurDto == null){
            return null;
        }
        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
        ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
        ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());
        ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));
        ligneCommandeFournisseur.setCommandeFournisseur(CommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto.getCommandeFournisseur()));

        return ligneCommandeFournisseur;
    }

}
