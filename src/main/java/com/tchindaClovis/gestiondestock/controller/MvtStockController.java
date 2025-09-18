package com.tchindaClovis.gestiondestock.controller;

import com.tchindaClovis.gestiondestock.controller.api.MvtStockApi;
import com.tchindaClovis.gestiondestock.dto.MvtStockDto;
import com.tchindaClovis.gestiondestock.services.MvtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
public class MvtStockController implements MvtStockApi {

    private MvtStockService mvtStockService;

    @Autowired
    public MvtStockController(MvtStockService mvtStockService) {
        this.mvtStockService = mvtStockService;
    }

    @Override
    public MvtStockDto save(MvtStockDto dto) {
        return mvtStockService.save(dto);
    }

    @Override
    public MvtStockDto findById(Integer id) {
        return mvtStockService.findById(id);
    }

    @Override
    public MvtStockDto findByDateMvt(Instant dateMvt) {
        return mvtStockService.findByDateMvt(dateMvt);
    }

    @Override
    public MvtStockDto findByQuantite(BigDecimal quantite) {
        return mvtStockService.findByQuantite(quantite);
    }

    @Override
    public List<MvtStockDto> findAll() {
        return mvtStockService.findAll();
    }

    @Override
    public void delete(Integer id) {
        mvtStockService.delete(id);
    }
}
