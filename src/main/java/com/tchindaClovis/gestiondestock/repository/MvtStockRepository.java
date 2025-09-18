package com.tchindaClovis.gestiondestock.repository;

import com.tchindaClovis.gestiondestock.model.Client;
import com.tchindaClovis.gestiondestock.model.CommandeFournisseur;
import com.tchindaClovis.gestiondestock.model.MvtStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

public interface MvtStockRepository extends JpaRepository<MvtStock, Integer> {
    Optional<MvtStock> findMvtStockByDateMvt(Instant dateMvt);
    Optional<MvtStock> findMvtStockByQuantite(BigDecimal quantite);
}
