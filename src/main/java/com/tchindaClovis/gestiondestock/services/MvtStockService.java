package com.tchindaClovis.gestiondestock.services;

import com.tchindaClovis.gestiondestock.dto.MvtStockDto;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface MvtStockService {
    MvtStockDto save(MvtStockDto dto);
    MvtStockDto findById(Integer id);
    MvtStockDto findByDateMvt(Instant dateMvt);
    MvtStockDto findByQuantite(BigDecimal quantite);
    List<MvtStockDto> findAll();
    void delete(Integer id);
}
