package com.tchindaClovis.gestiondestock.dto;
import com.tchindaClovis.gestiondestock.model.MvtStock;
import com.tchindaClovis.gestiondestock.model.TypeMvtStock;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStockDto {

    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMvtStock typeMvtStock;

    public static MvtStockDto fromEntity (MvtStock mvtStock){
        if(mvtStock == null){
            return null;
        }
        return MvtStockDto.builder()
                .id(mvtStock.getId())
                .dateMvt(mvtStock.getDateMvt())
                .quantite(mvtStock.getQuantite())
                .article(ArticleDto.fromEntity(mvtStock.getArticle()))
                .build();
    }

    public static MvtStock toEntity (MvtStockDto mvtStockDto){
        if(mvtStockDto == null){
            return null;
        }
        MvtStock mvtStock = new MvtStock();
        mvtStock.setId(mvtStockDto.getId());
        mvtStock.setDateMvt(mvtStockDto.getDateMvt());
        mvtStock.setQuantite(mvtStockDto.getQuantite());
        mvtStock.setArticle(ArticleDto.toEntity(mvtStockDto.getArticle()));

        return mvtStock;
    }
}
