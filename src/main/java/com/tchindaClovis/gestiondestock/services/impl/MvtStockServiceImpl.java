package com.tchindaClovis.gestiondestock.services.impl;

import com.tchindaClovis.gestiondestock.dto.MvtStockDto;
import com.tchindaClovis.gestiondestock.exception.EntityNotFoundException;
import com.tchindaClovis.gestiondestock.exception.ErrorCodes;
import com.tchindaClovis.gestiondestock.exception.InvalidEntityException;
import com.tchindaClovis.gestiondestock.model.MvtStock;
import com.tchindaClovis.gestiondestock.repository.MvtStockRepository;
import com.tchindaClovis.gestiondestock.services.MvtStockService;
import com.tchindaClovis.gestiondestock.validator.MvtStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStockServiceImpl implements MvtStockService {
    private MvtStockRepository mvtStockRepository;

    @Autowired
    public MvtStockServiceImpl(MvtStockRepository mvtStockRepository) {
        this.mvtStockRepository = mvtStockRepository;
    }

    @Override
    public MvtStockDto save(MvtStockDto dto) {
        List<String> errors = MvtStockValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Mouvement de stock is not valid{}", dto);
            throw new InvalidEntityException("Le mouvement de stock n'est pas valide",
                    ErrorCodes.MVT_STOCK_NOT_VALID, errors);
        }
        MvtStock savedMvtStock = mvtStockRepository.save(MvtStockDto.toEntity(dto));
        return MvtStockDto.fromEntity(savedMvtStock);
    }

    @Override
    public MvtStockDto findById(Integer id) {
        if(id == null){
            log.error("Mouvement de stock ID is null");
            return null;
        }
        Optional<MvtStock> mvtStock = mvtStockRepository.findById(id);

        return Optional.of(MvtStockDto.fromEntity(mvtStock.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun mouvement de stock avec l'ID = " + id + "n'a ete trouve dans la BDD",
                        ErrorCodes.MVT_STOCK_NOT_FOUND)
        );
    }

    @Override
    public MvtStockDto findByDateMvt(Instant dateMvt) {
        if (dateMvt == null){
            log.error("Mouvement de stock DATEMVT is null");
            return null;
        }
        Optional<MvtStock> mvtStock = mvtStockRepository.findMvtStockByDateMvt(dateMvt);
        return Optional.of(MvtStockDto.fromEntity(mvtStock.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun mouvement de stock avec le DATEMVT = " + dateMvt + "n'a ete trouve dans la BDD",
                        ErrorCodes.MVT_STOCK_NOT_FOUND)
        );
    }

    @Override
    public MvtStockDto findByQuantite(BigDecimal quantite) {
        if (quantite == null){
            log.error("Mouvement de stock QUANTITE is null");
            return null;
        }
        Optional<MvtStock> mvtStock = mvtStockRepository.findMvtStockByQuantite(quantite);
        return Optional.of(MvtStockDto.fromEntity(mvtStock.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun mouvement de stock avec le QUANTITE= " + quantite + "n'a ete trouve dans la BDD",
                        ErrorCodes.MVT_STOCK_NOT_FOUND)
        );
    }

    @Override
    public List<MvtStockDto> findAll() {
        return mvtStockRepository.findAll().stream()
                .map(MvtStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("mouvement stock ID is null");
            return;
        }
        mvtStockRepository.deleteById(id);
    }
}
