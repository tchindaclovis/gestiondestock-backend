package com.tchindaClovis.gestiondestock.services.impl;

import com.tchindaClovis.gestiondestock.dto.ClientDto;
import com.tchindaClovis.gestiondestock.dto.FournisseurDto;
import com.tchindaClovis.gestiondestock.exception.EntityNotFoundException;
import com.tchindaClovis.gestiondestock.exception.ErrorCodes;
import com.tchindaClovis.gestiondestock.exception.InvalidEntityException;
import com.tchindaClovis.gestiondestock.model.Client;
import com.tchindaClovis.gestiondestock.model.Fournisseur;
import com.tchindaClovis.gestiondestock.repository.FournisseurRepository;
import com.tchindaClovis.gestiondestock.services.FournisseurService;
import com.tchindaClovis.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Fournisseur is not valid{}", dto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        Fournisseur savedFournisseur = fournisseurRepository.save(FournisseurDto.toEntity(dto));
        return FournisseurDto.fromEntity(savedFournisseur);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if(id == null){
            log.error("Fournisseur ID is null");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID = " + id + "n'a ete trouve dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
    }

    @Override
    public FournisseurDto findByNom(String nom) {
        if(!StringUtils.hasLength(nom)){
            log.error("Fournisseur NOM is null");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findByNom(nom);
        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun fournisseur avec le NOM = " + nom + "n'a ete trouve dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
    }


    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Fournisseur ID is null");
            return;
        }
        fournisseurRepository.deleteById(id);
    }
}
