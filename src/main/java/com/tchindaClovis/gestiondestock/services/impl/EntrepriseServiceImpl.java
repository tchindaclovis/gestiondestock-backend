package com.tchindaClovis.gestiondestock.services.impl;

import com.tchindaClovis.gestiondestock.dto.EntrepriseDto;
import com.tchindaClovis.gestiondestock.exception.EntityNotFoundException;
import com.tchindaClovis.gestiondestock.exception.ErrorCodes;
import com.tchindaClovis.gestiondestock.exception.InvalidEntityException;
import com.tchindaClovis.gestiondestock.model.Entreprise;
import com.tchindaClovis.gestiondestock.repository.EntrepriseRepository;
import com.tchindaClovis.gestiondestock.services.EntrepriseService;
import com.tchindaClovis.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Entreprise is not valid{}", dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        Entreprise savedEntreprise = entrepriseRepository.save(EntrepriseDto.toEntity(dto));
        return EntrepriseDto.fromEntity(savedEntreprise);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if(id == null){
            log.error("Entreprise ID is null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);

        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec l'ID = " + id + "n'a ete trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
    }

    @Override
    public EntrepriseDto findByNomEntreprise(String nomEntreprise) {
        if(!StringUtils.hasLength(nomEntreprise)){
            log.error("Entreprise NOM is null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findEntrepriseByNomEntreprise(nomEntreprise);
        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec le NOM = " + nomEntreprise + "n'a ete trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
    }

    @Override
    public EntrepriseDto findByCodeFiscal(String codeFiscal) {
        if(!StringUtils.hasLength(codeFiscal)){
            log.error("Entreprise CODEFISCAL is null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findEntrepriseByCodeFiscal(codeFiscal);
        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec le CODEFISCAL = " + codeFiscal + "n'a ete trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
