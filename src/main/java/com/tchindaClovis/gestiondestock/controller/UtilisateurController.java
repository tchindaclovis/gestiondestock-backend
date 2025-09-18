package com.tchindaClovis.gestiondestock.controller;

import com.tchindaClovis.gestiondestock.controller.api.UtilisateurApi;
import com.tchindaClovis.gestiondestock.dto.UtilisateurDto;
import com.tchindaClovis.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto findByNomUtilisateur(String nomUtilisateur) {
        return utilisateurService.findByNomUtilisateur(nomUtilisateur);
    }

    @Override
    public UtilisateurDto findByPrenomUtilisateur(String prenomUtilisateur) {
        return utilisateurService.findByPrenomUtilisateur(prenomUtilisateur);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
