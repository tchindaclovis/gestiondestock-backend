package com.tchindaClovis.gestiondestock.services.impl;

import com.tchindaClovis.gestiondestock.dto.CommandeClientDto;
import com.tchindaClovis.gestiondestock.dto.LigneVenteDto;
import com.tchindaClovis.gestiondestock.dto.VenteDto;
import com.tchindaClovis.gestiondestock.exception.EntityNotFoundException;
import com.tchindaClovis.gestiondestock.exception.ErrorCodes;
import com.tchindaClovis.gestiondestock.exception.InvalidEntityException;
import com.tchindaClovis.gestiondestock.model.Article;
import com.tchindaClovis.gestiondestock.model.LigneVente;
import com.tchindaClovis.gestiondestock.model.Vente;
import com.tchindaClovis.gestiondestock.repository.ArticleRepository;
import com.tchindaClovis.gestiondestock.repository.LigneVenteRepository;
import com.tchindaClovis.gestiondestock.repository.VenteRepository;
import com.tchindaClovis.gestiondestock.services.VenteService;
import com.tchindaClovis.gestiondestock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {
    private ArticleRepository articleRepository;
    private VenteRepository venteRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VenteServiceImpl(ArticleRepository articleRepository, VenteRepository
            venteRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.venteRepository = venteRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        List<String> errors = VenteValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Vente n'est pas valide");
            throw new InvalidEntityException("Vente non valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if(article.isEmpty()){
                articleErrors.add("Aucun article avec l'ID" + ligneVenteDto.getArticle().getId() +
                        "n'a été trouvé dans la BDD");
            }
        });

        if(!articleErrors.isEmpty()){
            log.error("one or more articles were not found in the DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas été trouvé dan la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Vente savedVente = venteRepository.save(VenteDto.toEntity(dto));

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
        });

        return VenteDto.fromEntity(savedVente);
    }

    @Override
    public VenteDto findById(Integer id) {
        if(id == null){
            log.error("Vente ID is NULL");
            return null;
        }
        return venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente n'a été trouvé avec l'ID " + id,ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public VenteDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Vente CODE is null");
            return null;
        }
        return venteRepository.findVenteByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente n'a été trouvé avec le CODE " +
                                code,ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Vente ID is NULL");
            return ;
        }
        venteRepository.deleteById(id);
    }
}
