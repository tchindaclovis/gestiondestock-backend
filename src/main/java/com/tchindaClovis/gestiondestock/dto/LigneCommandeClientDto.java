package com.tchindaClovis.gestiondestock.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tchindaClovis.gestiondestock.model.CommandeClient;
import com.tchindaClovis.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private ArticleDto article;
    @JsonIgnore
    private CommandeClientDto commandeClient;

    public static LigneCommandeClientDto fromEntity (LigneCommandeClient ligneCommandeClient){  //permet de faire un mapping de l'entité vers le DTO
        if(ligneCommandeClient == null){
            return null;
        }

        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .commandeClient(CommandeClientDto.fromEntity(ligneCommandeClient.getCommandeClient()))
                .build();
    }

    public static LigneCommandeClient toEntity (LigneCommandeClientDto ligneCommandeClientDto){    //permet de faire un mapping du DTO vers l'entité
        if(ligneCommandeClientDto == null){
            return null;
        }
        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientDto.getId());
        ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
        ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
        ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeClient()));
        return ligneCommandeClient;
    }
}
